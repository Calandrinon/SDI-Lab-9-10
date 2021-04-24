package ro.ubb.catalog.core.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistsDto {
    private Collection<ArtistDto> artists;
}
