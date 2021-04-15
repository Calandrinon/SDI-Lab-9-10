package ro.ubb.catalog.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.exceptions.*;
import ro.ubb.catalog.core.model.*;
import ro.ubb.catalog.core.repository.*;
import ro.ubb.catalog.web.converter.UserConverter;
import ro.ubb.catalog.web.dto.UserDto;
import ro.ubb.catalog.web.dto.UsersDto;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class UserController {
    @Autowired
    private JPAUserRepository userRepository;
    @Autowired
    private ExecutorService executor;
    @Autowired
    private UserConverter userConverter;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * creates and adds to the repository a user
     *

     *
     * @throws RuntimeException
     *              if the element has already been added to the list
     *
     */


    // @Override
    @PostMapping("/user")
    public ResponseEntity<UserDto> add(@RequestBody UserDto userDto) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            User user = this.userConverter.convertDtoToModel(userDto);
            this.userRepository.save(user);
            try {
                return ResponseEntity.created(new URI("/user/" + user.getId())).body(userDto);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }, this.executor).get();
    }

    /**
     * creates and updates to the repository the user with another one having the same id
     */

    // @Override
    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Integer id, @RequestBody UserDto userDto) throws ExecutionException, InterruptedException {
        return CompletableFuture.supplyAsync(() -> {
            User user = this.userConverter.convertDtoToModel(userDto);
            user.setId(id);
            this.userRepository.save(user);
            return ResponseEntity.ok(userDto);
        }, this.executor).get();
    }

    /**
     *
     * @param id removes from the repository the user having this id
     * @throws Exception
     *              if the element is not found inside the repository
     */

    // @Override
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Integer> remove(@PathVariable Integer id) throws Exception {
        return CompletableFuture.supplyAsync(() -> {
            User user = new User();
            user.setId(id);
            this.userRepository.delete(user);
            return ResponseEntity.ok(id);
        }, this.executor).get();
    }

    /**
     *
     * @return the a list of String values corresponding to the user objects
     */

    private final BinaryOperator<String> createListOfStrings = (a, b) -> a + ", " + b;

    // @Override
    @RequestMapping("/users")
    public UsersDto getRepository() throws ExecutionException, InterruptedException {
        logger.atDebug().log("requested repository");

        return new UsersDto(CompletableFuture.supplyAsync(() -> {
            return this.userRepository.findAll();
        }, this.executor).get().stream().map(e -> this.userConverter.convertModelToDto(e)).collect(Collectors.toSet()));
    }


    /**
     *
     * @param minimumNumberOfTransactions the lower bound for the Users' number of transactions
     * @return all of the users having more transactions than the given minimum amount
     */

    // @Override
    @RequestMapping("/userFilter/{minimumNumberOfTransactions}")
    public UsersDto filterByNumberOfTransactions(@PathVariable Integer minimumNumberOfTransactions) throws ExecutionException, InterruptedException {
        return new UsersDto(CompletableFuture.supplyAsync(() -> {
            return this.userRepository.findByNumberOfTransactionsGreaterThanEqual(minimumNumberOfTransactions);
        }, this.executor).get().stream().map(e -> this.userConverter.convertModelToDto(e)).collect(Collectors.toSet()));
    }

    /**
     *
     * @param userID the id of the user you want to obtain
     * @return a User wrapped inside an Optional or an empty Ooptional
     */

    // @Override
    @GetMapping("/existUser/{userID}")
     public ResponseEntity<Boolean> exists(@PathVariable Integer userID) throws ExecutionException, InterruptedException {
        return ResponseEntity.ok(CompletableFuture.supplyAsync(() -> {
            return this.userRepository.findOne(userID).isPresent();
        }, this.executor).get());
     }
}
