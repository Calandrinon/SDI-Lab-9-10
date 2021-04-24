package ro.ubb.catalog.core.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.dto.ArtistDto;
import ro.ubb.catalog.core.model.Artist;


@Component
public class ArtistConverter extends BaseConverter<Artist, ArtistDto>{
    @Override
    public Artist convertDtoToModel(ArtistDto dto) {
        return new Artist(dto.getName(), dto.getEstablishmentYear());
    }

    @Override
    public ArtistDto convertModelToDto(Artist artist) {
        return new ArtistDto(artist.getName(), artist.getEstablishmentYear());
    }
}
