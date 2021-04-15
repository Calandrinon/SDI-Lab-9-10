package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface JPARecordRepository extends JpaRepository<Record, Integer> {
    @Query("SELECT r FROM Record r WHERE r.id = ?1")
    Optional<Record> findOne(int id);

    List<Record> findByPriceLessThanEqual(int price);

    //@Query("SELECT r FROM Record r WHERE r.inStock > ?1")
    List<Record> findByInStockGreaterThan(int inStock);
}
