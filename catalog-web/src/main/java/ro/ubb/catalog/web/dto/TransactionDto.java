package ro.ubb.catalog.web.dto;

import lombok.*;
import ro.ubb.catalog.core.model.Record;
import ro.ubb.catalog.core.model.User;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransactionDto extends BaseEntityDto{
    private Integer userID;
    private Integer recordID;
    private Integer quantity;
}