package ro.ubb.catalog.core.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.ubb.catalog.core.model.User;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDto {
    private Collection<UserDto> users;
}
