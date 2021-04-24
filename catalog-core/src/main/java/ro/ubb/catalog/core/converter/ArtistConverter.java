package ro.ubb.catalog.core.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.dto.ArtistDto;
import ro.ubb.catalog.core.model.Artist;


@Component
public class ArtistConverter extends BaseConverter<Artist, ArtistDto>{
    @Override
    public Artist convertDtoToModel(ArtistDto dto) {
        Artist artist = new Artist(dto.getName(), dto.getEstablishmentYear());
        artist.setId(dto.getId());
        return artist;
    }

    @Override
    public ArtistDto convertModelToDto(Artist artist) {
        ArtistDto artistDto = new ArtistDto(artist.getName(), artist.getEstablishmentYear());
        artistDto.setId(artist.getId());
        return artistDto;
    }
}
