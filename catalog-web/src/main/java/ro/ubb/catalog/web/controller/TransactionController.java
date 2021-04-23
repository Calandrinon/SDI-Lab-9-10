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
import ro.ubb.catalog.core.service.RecordService;
import ro.ubb.catalog.core.service.TransactionService;
import ro.ubb.catalog.core.service.UserService;
import ro.ubb.catalog.core.converter.RecordConverter;
import ro.ubb.catalog.core.converter.TransactionConverter;
import ro.ubb.catalog.core.dto.RecordsDto;
import ro.ubb.catalog.core.dto.TransactionDto;
import ro.ubb.catalog.core.dto.TransactionMapDto;
import ro.ubb.catalog.core.dto.TransactionsDto;

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
    private TransactionService transactionService;
    @Autowired
    private UserService userService;
    @Autowired
    private RecordService recordService;
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
            this.transactionService.makeTransaction(this.transactionConverter.convertDtoToModel(transactionDto));
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
            return new TransactionMapDto(this.transactionService.filterByUser(userID).stream()
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
            return transactionService.getAll();
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
            List<Transaction> list = transactionService.filterByDate(actualDate);
            logger.atDebug().log(list.toString());
            return list;
        }, this.executor).get().stream().map(e -> this.transactionConverter.convertModelToDto(e)).collect(Collectors.toSet()));
    }


    /**
     *
     * @return the record having the maximum amount of purchases
     */

    // @Override
    @RequestMapping(value = "/mostpurchased")
    public ResponseEntity<Record> getMostPurchasedRecords() throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(CompletableFuture.supplyAsync(() -> {
            return this.recordService.getAll().stream()
                    .max(Comparator.comparingInt(r -> this.transactionService.getTotalPurchasedByID(r.getId())));
        }, this.executor).get().get());
    }
}
