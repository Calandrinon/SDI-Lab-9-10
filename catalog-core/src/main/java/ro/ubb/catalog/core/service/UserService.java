package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import ro.ubb.catalog.core.dto.UserDto;
import ro.ubb.catalog.core.dto.UsersDto;
import ro.ubb.catalog.core.exceptions.ValidationException;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;


public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    public CompletableFuture<String> add(Integer id, String FirstName, String LastName, Integer NumberOfTransactions) throws ValidationException, SQLException, ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            String url = "http://localhost:8080/api/user";
            UserDto userDto = new UserDto(FirstName, LastName, NumberOfTransactions);
            userDto.setId(id);
            restTemplate.postForObject(url, userDto, UserDto.class);

            return "user successfully added";
        });
    }

    public CompletableFuture<String> update(Integer id, String FirstName, String LastName, Integer NumberOfTransactions) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String url = "http://localhost:8080/api/user/{id}";
                UserDto userDto = new UserDto(FirstName, LastName, NumberOfTransactions);
                userDto.setId(id);
                restTemplate.put(url, userDto, id);

                return "user successfully updated";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CompletableFuture<String> remove(Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            try {
                String url = "http://localhost:8080/api/user/{id}";

                restTemplate.delete(url, id);
                return "user successfully removed";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public CompletableFuture<List<String>> getRepository() throws SQLException, ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            String url = "http://localhost:8080/api/users";
            UsersDto usersDto = restTemplate.getForObject(url, UsersDto.class);

            return usersDto.getUsers().stream().map(UserDto::toString).collect(Collectors.toList());
        });
    }

    public Boolean exists(Integer userID) throws ExecutionException, InterruptedException, SQLException {
        String url = "http://localhost:8080/api/existUser/{id}";

        return restTemplate.getForObject(url, Boolean.class, userID);
    }

    public CompletableFuture<List<String>> filterByNumberOfTransactions(Integer minimumNumberOfTransactions) throws SQLException, ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            String url = "http://localhost:8080/api/userFilter/{minimumNumberOfTransactions}";
            UsersDto usersDto = restTemplate.getForObject(url, UsersDto.class, minimumNumberOfTransactions);
            return usersDto.getUsers().stream().map(UserDto::toString).collect(Collectors.toList());
        });
    }
}