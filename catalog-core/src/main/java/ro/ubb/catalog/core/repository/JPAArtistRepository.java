package ro.ubb.catalog.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.catalog.core.model.Artist;
import ro.ubb.catalog.core.model.Record;

import java.util.List;
import java.util.Optional;

@Repository
public interface JPAArtistRepository extends JpaRepository<Artist, Integer> {
    @Query("SELECT a FROM Artist a WHERE a.id = ?1")
    Optional<Artist> findOne(int id);

    List<Artist> findByEstablishmentYearLessThan(int establishmentYear);

    List<Artist> findAllByOrderByEstablishmentYearAsc();
}
