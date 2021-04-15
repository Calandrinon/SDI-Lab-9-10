package ro.ubb.catalog.core.dto;

import lombok.*;
import ro.ubb.catalog.core.model.RecordType;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RecordDto extends BaseEntityDto{
    private int price;
    private String albumName;
    private int inStock;
    private RecordType typeOfRecord;
}
