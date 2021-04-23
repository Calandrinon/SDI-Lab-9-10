package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.model.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TransactionService {
    void makeTransaction(Transaction transaction);

    Map<Integer, Integer> getRecordsByUser(int userId);

    List<Transaction> getAll();

    List<Transaction> filterByDate(Date date);

    Integer getTotalPurchasedByID(int id);

    Optional<Record> getMostPurchasedRecords();

    List<Transaction> filterByUser(int userId);
}
