package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.model.Transaction;
import ro.ubb.catalog.core.model.User;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface JPATransactionRepository extends JpaRepository<Transaction, Integer>{
    @Query("SELECT MAX(t.id) FROM Transaction t")
    public int getNextId();

    List<Transaction> findByUser(User user);

    List<Transaction> findByDate(Date date);

    List<Transaction> findByRecord(Record record);
}
