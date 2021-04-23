package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.model.Transaction;
import ro.ubb.catalog.core.model.User;
import ro.ubb.catalog.core.repository.JPARecordRepository;
import ro.ubb.catalog.core.repository.JPATransactionRepository;
import ro.ubb.catalog.core.repository.JPAUserRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    private JPAUserRepository userRepository;
    @Autowired
    private JPARecordRepository recordRepository;
    @Autowired
    private JPATransactionRepository transactionRepository;

    @Override
    public void makeTransaction(Transaction transaction) {
        Integer userID = transaction.getUser().getId();
        Integer recordID = transaction.getRecord().getId();
        int quantity = transaction.getQuantity();

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
    }

    @Override
    public Map<Integer, Integer> getRecordsByUser(int userId) {
        User user = new User();
        user.setId(userId);
        return this.transactionRepository.findByUser(user).stream()
                .collect(Collectors.toMap(t-> t.getRecord().getId(), Transaction::getQuantity, Integer::sum));
    }

    @Override
    public List<Transaction> getAll() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> filterByDate(Date date) {
        return transactionRepository.findByDate(date);
    }

    @Override
    public Integer getTotalPurchasedByID(int id) {
        Record record = new Record();
        record.setId(id);
        return this.transactionRepository.findByRecord(record).stream()
                .map(Transaction::getQuantity)
                .reduce(0, Integer::sum);
    }

    @Override
    public Optional<Record> getMostPurchasedRecords() {
        return this.recordRepository.findAll().stream()
                .max(Comparator.comparingInt(r -> getTotalPurchasedByID(r.getId())));
    }

    @Override
    public List<Transaction> filterByUser(int userId) {
        User user = this.userRepository.findOne(userId).get();
        return transactionRepository.findByUser(user);
    }
}
