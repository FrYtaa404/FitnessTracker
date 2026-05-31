package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserDtoSimple;

@Component
class UserMapper {

    UserDto toUserDto(User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getEmail());
    }

    public UserDtoSimple toUserDtoSimple(User user) {
        return new UserDtoSimple(user.getId(), user.getFirstName(), user.getLastName());
    }

    public User toUser(UserDto userDto){
        return new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
    }
}
