package ro.ubb.catalog.core.dto;

import lombok.*;

import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString(callSuper = true)
public class ArtistDto extends BaseEntityDto {
    private String name;
    private Integer establishmentYear;
}
