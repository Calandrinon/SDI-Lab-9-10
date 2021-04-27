package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.exceptions.ValidationException;
import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.repository.JPARecordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService{
    @Autowired
    private JPARecordRepository recordRepository;

    @Override
    public void addRecord(Record record) throws ValidationException {
        if (recordRepository.findOne(record.getId()).isEmpty()) {
            this.recordRepository.save(record);
        } else {
            throw new ValidationException("The record with the ID " + record.getId() + " already exists in the repository.");
        }
    }

    @Override
    public void updateRecord(Record record) {
        this.recordRepository.save(record);
    }

    @Override
    public void removeRecord(int id) {
        Record deleted = new Record();
        deleted.setId(id);
        this.recordRepository.delete(deleted);
    }

    @Override
    public Record getOne(int id) {
       return this.recordRepository.getOne(id);
    }

    @Override
    public List<Record> getAll() {
        return this.recordRepository.findAll();
    }

    @Override
    public List<Record> filterByPrice(int price) {
        return this.recordRepository.findByPriceLessThanEqual(price);
    }

    @Override
    public List<Record> filterByRecordsWithInStockGreaterThan(int minimumInStock) {
        return this.recordRepository.findByInStockGreaterThan(minimumInStock);
    }

    @Override
    public List<Record> getAllSortedByPriceAscendingly() {
        return this.recordRepository.findAllByOrderByPriceAsc();
    }
}
