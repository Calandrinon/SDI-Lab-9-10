package ro.ubb.catalog.core.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseEntityDto implements Serializable {
    private int id;
}
