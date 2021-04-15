package ro.ubb.catalog.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.catalog.core.model.User;
import ro.ubb.catalog.web.dto.UserDto;

@Component
public class UserConverter extends BaseConverter<User, UserDto>{
    @Override
    public User convertDtoToModel(UserDto dto) {
        var model = new User();
        model.setId(dto.getId());
        model.setFirstName(dto.getFirstName());
        model.setLastName(dto.getLastName());
        model.setNumberOfTransactions(dto.getNumberOfTransactions());
        return model;
    }

    @Override
    public UserDto convertModelToDto(User user) {
        UserDto userDto = new UserDto(user.getFirstName(), user.getLastName(), user.getNumberOfTransactions());
        userDto.setId(user.getId());
        return userDto;
    }
}
