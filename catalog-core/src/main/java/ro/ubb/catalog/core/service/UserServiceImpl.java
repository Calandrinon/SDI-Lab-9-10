package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.User;
import ro.ubb.catalog.core.repository.JPAUserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private JPAUserRepository userRepository;

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void removeUser(int id) {
        User deleted = new User();
        deleted.setId(id);
        userRepository.delete(deleted);
    }

    @Override
    public User getOne(int id) {
        return userRepository.getOne(id);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> filterByNumberOfTransactions(int numberOfTransactions) {
        return userRepository.findByNumberOfTransactionsGreaterThanEqual(numberOfTransactions);
    }

    @Override
    public boolean exists(int id) {
        return userRepository.findOne(id).isPresent();
    }
}
