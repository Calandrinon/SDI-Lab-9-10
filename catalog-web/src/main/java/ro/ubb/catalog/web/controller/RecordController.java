package ro.ubb.catalog.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.exceptions.*;
import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.core.repository.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import ro.ubb.catalog.web.converter.RecordConverter;
import ro.ubb.catalog.web.dto.RecordDto;
import ro.ubb.catalog.web.dto.RecordsDto;

@RestController
public class RecordController /*implements RecordControllerInterface*/ {

    @Autowired
    private JPARecordRepository recordRepository;
    @Autowired
    private ExecutorService executor;
    @Autowired
    private RecordConverter recordConverter;
    private final Logger logger = LoggerFactory.getLogger(RecordController.class);


    /**
     * creates and adds to the repository a record
     *

     * @throws RuntimeException
     *               if the element has already been added to the list
     */

    // @Override
    @RequestMapping(value = "/record", method = RequestMethod.POST)
    public ResponseEntity<RecordDto> add(@RequestBody RecordDto record) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            this.recordRepository.save(this.recordConverter.convertDtoToModel(record));
            try {
                return ResponseEntity.created(new URI("/record/" + record.getId())).body(record);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }, this.executor).get();
    }


    // @Override
    @RequestMapping(value = "/record/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RecordDto> update(@PathVariable Integer id, @RequestBody RecordDto record) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {

            Record r = this.recordConverter.convertDtoToModel(record);
            r.setId(id);
            this.recordRepository.save(r);
            return ResponseEntity.ok(record);
        }, this.executor).get();

//        logger.atDebug()
//                .addKeyValue("id", id)
//                .addKeyValue("price", price)
//                .addKeyValue("album", AlbumName)
//                .addKeyValue("stock", InStock)
//                .addKeyValue("type", RecordType)
//                .log("Record updated");
    }


    /**
     *
     * @param id must be already found in the repository
     * @throws RuntimeException
     *              if the element is not found inside the repository
     */

    // @Override
    @RequestMapping(value = "/record/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Integer> remove(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            Record record = new Record();
            record.setId(id);
            this.recordRepository.delete(record);
            return ResponseEntity.ok(id);
        }, this.executor).get();
    }

    /**
     *
     * @param recordID the id of the element we are looking for
     * @return the String equivalent of the record
     */

    public String getRecordByID(Integer recordID) throws SQLException {
        return this.recordRepository.getOne(recordID).toString();
    }

    /**
     *
     * @return the the a list of String values corresponding to the record objects
     */

    private final BinaryOperator<String> createListOfStrings = (a, b) -> a + ", " + b;

    // @Override
    @RequestMapping(value = "/records")
    public RecordsDto getRepository() throws ExecutionException, InterruptedException {
        logger.atDebug().log("repository accessed");
        logger.info("repo accessed");

        return new RecordsDto(CompletableFuture.supplyAsync(() -> {
            return this.recordRepository.findAll();
        }, this.executor).get().stream().map(e -> this.recordConverter.convertModelToDto(e)).collect(Collectors.toSet()));
    }

    /**
     *
     * @param maximumPrice the maximum price of the
     * @return a list of all records having the value of the price attribute smaller than the given @maximumPrice
     */

    // @Override
    @GetMapping("/recordFilter/{maximumPrice}")
    public RecordsDto filterByPrice(@PathVariable int maximumPrice) throws SQLException, ExecutionException, InterruptedException {
        logger.atDebug().log("repository accessed");

        return new RecordsDto(CompletableFuture.supplyAsync(() -> {
            return this.recordRepository.findByPriceLessThanEqual(maximumPrice);
        }, this.executor).get().stream().map(e -> this.recordConverter.convertModelToDto(e)).collect(Collectors.toSet()));
    }


    @RequestMapping("/greaterThan/{minimumInStock}")
    public RecordsDto filterByRecordsWithInStockGreaterThan(@PathVariable int minimumInStock) throws ExecutionException, InterruptedException {
        logger.atDebug().log("repository accessed");

        return new RecordsDto(CompletableFuture.supplyAsync(() -> {
            return this.recordRepository.findByInStockGreaterThan(minimumInStock);
        }, this.executor).get().stream().map(e -> this.recordConverter.convertModelToDto(e)).collect(Collectors.toSet()));
    }
}
