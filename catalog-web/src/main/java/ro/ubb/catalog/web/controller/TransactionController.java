package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.exceptions.TransactionException;
import ro.ubb.catalog.core.exceptions.ValidationException;
import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.core.repository.*;
import ro.ubb.catalog.web.converter.RecordConverter;
import ro.ubb.catalog.web.converter.TransactionConverter;
import ro.ubb.catalog.web.dto.RecordsDto;
import ro.ubb.catalog.web.dto.TransactionDto;
import ro.ubb.catalog.web.dto.TransactionMapDto;
import ro.ubb.catalog.web.dto.TransactionsDto;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static java.lang.Math.abs;

@RestController
public class TransactionController {
    @Autowired
    private JPAUserRepository userRepository;
    @Autowired
    private JPARecordRepository recordRepository;
    @Autowired
    private JPATransactionRepository transactionRepository;
    @Autowired
    private ExecutorService executor;
    @Autowired
    private TransactionConverter transactionConverter;
    @Autowired
    private RecordConverter recordConverter;
    private final Logger logger = LoggerFactory.getLogger(TransactionController.class);


    /**
     *
     * @throws RuntimeException
     *              throws TransactionException if the record IDs are non existent
     *
     */

    // @Override
    @RequestMapping(value = "/transaction", method = RequestMethod.POST)
    public ResponseEntity<TransactionDto> makeTransaction(@RequestBody TransactionDto transactionDto) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() ->
        {
            Integer userID = transactionDto.getUserID();
            Integer recordID = transactionDto.getRecordID();
            Integer quantity = transactionDto.getQuantity();

            User user = userRepository.findOne(userID).get();
            Record record = recordRepository.findOne(recordID).get();

            User updatedUser = new User();
            updatedUser.setId(user.getId());
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            updatedUser.setNumberOfTransactions(user.getNumberOfTransactions() + 1);

            this.userRepository.save(updatedUser);

            Record updatedRecord = new Record(record.getPrice(), record.getAlbumName(), record.getInStock() - quantity, record.getTypeOfRecord());
            updatedRecord.setId(record.getId());
            this.recordRepository.save(updatedRecord);


            Transaction newTransaction = new Transaction(updatedUser, updatedRecord, new Date(), quantity);
            if(this.transactionRepository.findAll().isEmpty()) {
                newTransaction.setId(1);
            }
            else {
                newTransaction.setId(this.transactionRepository.getNextId() + 1);
            }
            this.transactionRepository.save(newTransaction);
            return ResponseEntity.ok(transactionDto);
        }, this.executor).get();
    }

    /**
     *
     * @param userID the id of the user
     * @return a map containing the record id as key and the the quantity as value,
     * if we encounter a duplicate key then the we execute integer sum on the two values under the same key
     */

    // @Override
    @RequestMapping(value = "/transaction/{userID}")
    public TransactionMapDto getRecordsByUser(@PathVariable Integer userID) throws ExecutionException, InterruptedException {
        logger.atDebug()
                .addKeyValue("userID", userID)
                .log("requested records for a user");

        return CompletableFuture.supplyAsync(() -> {
            User user = new User();
            user.setId(userID);
            return new TransactionMapDto(this.transactionRepository.findByUser(user).stream()
                    .collect(Collectors.toMap(t-> t.getRecord().getId(), Transaction::getQuantity, Integer::sum)));
        }, this.executor).get();
    }

    /**
     *
     * the a list of String values corresponding to the transaction objects
     */

    private final BinaryOperator<String> createListOfStrings = (a, b) -> a + ", " + b;

    // @Override
    @RequestMapping("/transactions")
    public TransactionsDto getRepository() throws ExecutionException, InterruptedException {
        logger.atDebug().log("requested repository");

        return new TransactionsDto(CompletableFuture.supplyAsync(() -> {
            return transactionRepository.findAll();
        }, this.executor).get().stream().map(e -> this.transactionConverter.convertModelToDto(e)).collect(Collectors.toSet()));
    }

    /**
     *
     * @param date the date to filter by
     * @return returns all the transactions which happened on the same day as the given date as Strings
     */

    // @Override
    @GetMapping("/datefilter/{date}")
    public TransactionsDto filterByDate(@PathVariable String date) throws ExecutionException, InterruptedException, ParseException {
        logger.atDebug().log("requested repository");
        logger.atDebug().log("=================:" + date + ":=================");
        Date actualDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").parse(date);

        return new TransactionsDto(CompletableFuture.supplyAsync(() -> {
            List<Transaction> list = transactionRepository.findByDate(actualDate);
            logger.atDebug().log(list.toString());
            return list;
        }, this.executor).get().stream().map(e -> this.transactionConverter.convertModelToDto(e)).collect(Collectors.toSet()));
    }

    /**
     *
     * @param id the id of the record you want the sum calculated for
     * @return the total sum of the record having the id = @id found in the transaction repository
     */

    private Integer getTotalPurchasedByID(Integer id) {
        Record record = new Record();
        record.setId(id);
        return this.transactionRepository.findByRecord(record).stream()
                .map(Transaction::getQuantity)
                .reduce(0, Integer::sum);
    }

    /*
    public Integer getTotalQuantityPurchasedByRecordID(Integer id) {
        return StreamSupport.stream(this.TransactionRepository.findAll().spliterator(), false)
                .filter(t -> t.getRecordID() == id)
                .map(Transaction::getQuantity)
                .reduce(0, Integer::sum);
    }
    */

    /**
     *
     * @return the record having the maximum amount of purchases
     */

    // @Override
    @RequestMapping(value = "/mostpurchased")
    public ResponseEntity<Record> getMostPurchasedRecords() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(CompletableFuture.supplyAsync(() -> {
            return this.recordRepository.findAll().stream()
                    .max(Comparator.comparingInt(r -> getTotalPurchasedByID(r.getId())));
        }, this.executor).get().get());
    }
}
