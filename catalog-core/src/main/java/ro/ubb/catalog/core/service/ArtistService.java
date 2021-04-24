package ro.ubb.catalog.core.service;

import ro.ubb.catalog.core.model.Artist;
import ro.ubb.catalog.core.model.Record;

import java.util.List;

public interface ArtistService {
    void addArtist(Artist artist);

    void updateArtist(Artist artist);

    void removeArtist(int id);

    Artist getOne(int id);

    List<Artist> getAll();

    List<Artist> filterByMaximumEstablishmentYear(int establishmentYear);

    List<Artist> getAllSortedByEstablishmentYearAscendingly();
}
