package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * UserController is responsible for handling HTTP requests related to user operations.
 * It provides endpoints for retrieving and creating users.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    private final UserProvider userProvider;

    private final UserMapper userMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody UserDto userDto) throws InterruptedException, UserKeyException {

        // TODO: Implement the method to add a new user.
        //  You can use the @RequestBody annotation to map the request body to the UserDto object.

        if (userDto.id() != null) throw new UserKeyException("Users key is generated automatically");

        var user = userMapper.toUser(userDto);

        user = userService.createUser(user);

        if (user == null) throw new UserKeyException("User cannot be created");

        return userDto;
    }

    @GetMapping
    public List<UserDto> getUsers() throws InterruptedException {

        return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toUserDto)
                .toList();
    }

    @GetMapping("/simple")
    public List<UserDtoSimple> getUsersSimple() throws InterruptedException {

        return this.userProvider.findAllUsers().stream()
                .map(this.userMapper::toUserDtoSimple)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserDetail(@PathVariable Long id) throws InterruptedException, UserNotFoundException {

        if (id <= 0L) throw new UserKeyException("Invalid id value: " + id);

        var userOptional = userProvider.getUser(id);

        if (userOptional.isEmpty()) throw new UserNotFoundException(id);

        return userMapper.toUserDto(userOptional.get());
    }

    @GetMapping("/email")
    public List<UserDto> getUserDetailByEmail(@RequestParam String email) throws InterruptedException, UserKeyException {

        return userProvider.findAllUsers()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .map(userMapper::toUserDto)
                .toList();
    }

    @GetMapping("/older/{localDate}")
    public List<UserDto> getUsersOlderThan(@PathVariable LocalDate localDate) throws InterruptedException, UserKeyException, UserNotFoundException {

        return userProvider.findAllUsers()
                .stream()
                .filter(user -> user.getBirthdate().isBefore(localDate))
                .map(userMapper::toUserDto)
                .toList();
    }




}