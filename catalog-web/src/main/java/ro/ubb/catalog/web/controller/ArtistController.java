package ro.ubb.catalog.web.controller;

import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.catalog.core.converter.ArtistConverter;
import ro.ubb.catalog.core.dto.ArtistDto;
import ro.ubb.catalog.core.dto.ArtistsDto;
import ro.ubb.catalog.core.dto.RecordsDto;
import ro.ubb.catalog.core.model.Artist;
import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.service.ArtistService;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

@RestController
public class ArtistController {
    @Autowired
    private ArtistService artistService;
    @Autowired
    private ExecutorService executor;
    @Autowired
    private ArtistConverter artistConverter;
    private final Logger logger = LoggerFactory.getLogger(ArtistController.class);

    @PostMapping("/artist")
    public ResponseEntity<ArtistDto> addArtist(@RequestBody ArtistDto artistDto) throws ExecutionException, InterruptedException {
        logger.info("ArtistController: Add the new artist with the ID " + artistDto.getId());

        return CompletableFuture.supplyAsync(() -> {
            this.artistService.addArtist(this.artistConverter.convertDtoToModel(artistDto));
            try {
                return ResponseEntity.created(new URI("/artist/" + artistDto.getId())).body(artistDto);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }, this.executor).get();
    }


    @PutMapping("/artist/{id}")
    public ResponseEntity<ArtistDto> updateArtist(@PathVariable Integer id, @RequestBody ArtistDto artistDto) throws ExecutionException, InterruptedException {
        logger.info("ArtistController: Update the artist with the ID " + id);

        return CompletableFuture.supplyAsync(() -> {
            Artist artist = this.artistConverter.convertDtoToModel(artistDto);
            artist.setId(id);
            this.artistService.updateArtist(artist);
            return ResponseEntity.ok(artistDto);
        }, this.executor).get();
    }


    @DeleteMapping("/artist/{id}")
    public ResponseEntity<Integer> removeArtist(@PathVariable Integer id) throws ExecutionException, InterruptedException {
        logger.info("ArtistController: Remove the artist with the ID " + id);

        return CompletableFuture.supplyAsync(() -> {
            this.artistService.removeArtist(id);
            return ResponseEntity.ok(id);
        }, this.executor).get();
    }


    @RequestMapping("/artists")
    public ArtistsDto getArtists() throws ExecutionException, InterruptedException {
        logger.info("ArtistController: Get all artists");

        return new ArtistsDto(CompletableFuture.supplyAsync(() -> {
            return this.artistService.getAll();
        }, this.executor).get().stream().map(e -> this.artistConverter.convertModelToDto(e)).collect(Collectors.toSet()));
    }


    @RequestMapping("/maximumEstablishmentYear/{maximumEstablishmentYear}")
    public ArtistsDto filterByMaximumEstablishmentYear(@PathVariable int maximumEstablishmentYear) throws ExecutionException, InterruptedException {
        logger.info("ArtistController: Filter artists by maximum establishment year");

        return new ArtistsDto(CompletableFuture.supplyAsync(() -> {
            return this.artistService.filterByMaximumEstablishmentYear(maximumEstablishmentYear);
        }, this.executor)
                .get().stream().map(artist -> this.artistConverter.convertModelToDto(artist)).collect(Collectors.toSet()));
    }


    @RequestMapping("/sortedByYear")
    public ArtistsDto getAllArtistsSortedByEstablishmentYearAscendingly() throws ExecutionException, InterruptedException {
        logger.info("ArtistController: Get all artists sorted by establishment year ascendingly");

        return new ArtistsDto(CompletableFuture.supplyAsync(() -> {
            return this.artistService.getAllSortedByEstablishmentYearAscendingly();
        }, this.executor)
                .get().stream().map(e -> this.artistConverter.convertModelToDto(e)).collect(Collectors.toList()));
    }
}
