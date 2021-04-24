package ro.ubb.catalog.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.catalog.core.model.Artist;
import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.repository.JPAArtistRepository;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private JPAArtistRepository artistRepository;

    @Override
    public void addArtist(Artist artist) {
        this.artistRepository.save(artist);
    }

    @Override
    public void updateArtist(Artist artist) {
        this.artistRepository.save(artist);
    }

    @Override
    public void removeArtist(int id) {
        Artist deletedArtist = new Artist();
        deletedArtist.setId(id);
        this.artistRepository.delete(deletedArtist);
    }

    @Override
    public Artist getOne(int id) {
        return this.artistRepository.getOne(id);
    }

    @Override
    public List<Artist> getAll() {
        return this.artistRepository.findAll();
    }

    @Override
    public List<Artist> filterByMaximumEstablishmentYear(int establishmentYear) {
        return this.artistRepository.findByEstablishmentYearLessThan(establishmentYear);
    }

    @Override
    public List<Artist> getAllSortedByEstablishmentYearAscendingly() {
        return this.artistRepository.findAllByOrderByEstablishmentYearAsc();
    }
}
