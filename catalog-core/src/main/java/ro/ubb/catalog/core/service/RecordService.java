package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Record;

import java.util.List;

public interface RecordService {
    void addRecord(Record record);

    void updateRecord(Record record);

    void removeRecord(int id);

    Record getOne(int id);

    List<Record> getAll();

    List<Record> filterByPrice(int price);

    List<Record> filterByRecordsWithInStockGreaterThan(int minimumInStock);

    List<Record> getAllSortedByPriceAscendingly();
}
