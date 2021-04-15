package ro.ubb.catalog.client.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.web.dto.TransactionDto;
import ro.ubb.catalog.web.dto.TransactionMapDto;
import ro.ubb.catalog.web.dto.TransactionsDto;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ro.ubb.catalog.core.model.Record;

public class TransactionService{
    @Autowired
    private RestTemplate restTemplate;
    private String baseUrl = "http://localhost:8080/api/";

    public CompletableFuture<String> makeTransaction(Integer userID, Integer recordID, Integer quantity) throws ExecutionException, InterruptedException{
        return CompletableFuture.supplyAsync(() -> {
            String url = baseUrl + "transaction";
            TransactionDto transactionDto = new TransactionDto(userID, recordID, quantity);

            return "your transaction: " + Objects.requireNonNull(restTemplate.postForObject(url, transactionDto, TransactionDto.class)).toString();
        });
    }

    public CompletableFuture<Map<Integer, Integer>> getRecordsByUser(Integer userID) throws ExecutionException, InterruptedException{
        return CompletableFuture.supplyAsync(() -> {
            String url = baseUrl + "transaction/{userID}";
            return Objects.requireNonNull(restTemplate.getForObject(url, TransactionMapDto.class, userID)).getMap();
        });
    }

    public CompletableFuture<List<String>> getRepository() throws ExecutionException, InterruptedException{
        return CompletableFuture.supplyAsync(() -> {
            String url = baseUrl + "transactions";
            return Objects.requireNonNull(restTemplate.getForObject(url, TransactionsDto.class)).getTransactions()
                    .stream()
                    .map(TransactionDto::toString)
                    .collect(Collectors.toList());
        });
    }

    public CompletableFuture<List<String>> filterByDate(Date date) throws ExecutionException, InterruptedException{
        return CompletableFuture.supplyAsync(() -> {
            String url = baseUrl + "datefilter/{date}";
            return Objects.requireNonNull(restTemplate.getForObject(url, TransactionsDto.class, date)).getTransactions()
                    .stream()
                    .map(TransactionDto::toString)
                    .collect(Collectors.toList());
        });
    }

    public CompletableFuture<List<String>> getMostPurchasedRecords() throws ExecutionException, InterruptedException{
        return CompletableFuture.supplyAsync(() -> {
            String url = baseUrl + "mostpurchased";

            return Stream.of(Objects.requireNonNull(restTemplate.getForObject(url, Record.class)).toString()).collect(Collectors.toList());
        });
    }
}