package ro.ubb.catalog.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.web.dto.RecordDto;
import ro.ubb.catalog.web.dto.RecordsDto;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class RecordService {
    private String url = "http://localhost:8080/api/";
    @Autowired
    private RestTemplate restTemplate;


    public CompletableFuture<String> add(Integer id, Integer price, String AlbumName, Integer InStock, RecordType RecordType) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            try {
                RecordDto recordDto = new RecordDto(price, AlbumName, InStock, RecordType);
                recordDto.setId(id);
                return "record: " +
                        Objects.requireNonNull(restTemplate.postForObject(this.url + "record", recordDto, RecordDto.class)).toString() +
                        " has been successfully added";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }
    public CompletableFuture<String> update(Integer id, Integer price, String AlbumName, Integer InStock, RecordType RecordType) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            try {
                this.restTemplate.put(this.url + "record/{id}",
                        new RecordDto(price, AlbumName, InStock, RecordType),
                        id);
                return "record has been updated";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    public CompletableFuture<String> remove(Integer id) throws Exception{
        return CompletableFuture.supplyAsync(() -> {
            try {
                this.restTemplate.delete(this.url + "record/{id}", id);
                return "record successfully removed";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
    public CompletableFuture<List<String>> getRepository() throws SQLException, ExecutionException, InterruptedException{
        return CompletableFuture.supplyAsync(() -> {
            return Objects.requireNonNull(this.restTemplate.getForObject(this.url + "records", RecordsDto.class))
                    .getRecords()
                    .stream()
                    .map(RecordDto::toString)
                    .collect(Collectors.toList());
        });
    }

    public CompletableFuture<List<String>> filterByPrice(Integer maximumPrice) throws SQLException, ExecutionException, InterruptedException{
        return CompletableFuture.supplyAsync(() -> {
            // @GetMapping("/recordFilter/{maximumPrice}")
            return Objects.requireNonNull(this.restTemplate
                    .getForObject(this.url + "recordFilter/{maximumPrice}", RecordsDto.class, maximumPrice))
                    .getRecords()
                    .stream()
                    .map(RecordDto::toString)
                    .collect(Collectors.toList());
        });
    }

    public CompletableFuture<List<String>> filterByRecordsWithInStockGreaterThan(Integer minimumInStock) {
        return CompletableFuture.supplyAsync(() -> {
            return Objects.requireNonNull(this.restTemplate
                    .getForObject(this.url + "greaterThan/{minimumInStock}", RecordsDto.class, minimumInStock))
                    .getRecords()
                    .stream()
                    .map(RecordDto::toString)
                    .collect(Collectors.toList());
        });
    }
}