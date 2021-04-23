package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    User getOne(int id);

    List<User> getAll();

    List<User> filterByNumberOfTransactions(int numberOfTransactions);

    boolean exists(int id);
}
