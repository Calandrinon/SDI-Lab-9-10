package ro.ubb.catalog.web.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserDto extends BaseEntityDto{
    private String firstName;
    private String lastName;
    private int numberOfTransactions;
}
