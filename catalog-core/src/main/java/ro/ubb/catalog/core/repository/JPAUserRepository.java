package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.catalog.core.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface JPAUserRepository extends JpaRepository<User, Integer> {
    @Query("SELECT u FROM User u WHERE u.id = ?1")
    Optional<User> findOne(int id);

    List<User> findByNumberOfTransactionsGreaterThanEqual(Integer minimumNumberOfTransactions);
}
