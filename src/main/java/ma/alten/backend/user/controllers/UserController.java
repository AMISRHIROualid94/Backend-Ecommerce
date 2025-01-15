package ma.alten.backend.user.controllers;

import jakarta.validation.Valid;
import ma.alten.backend.user.dto.UserDto;
import ma.alten.backend.user.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto userDto) throws NoSuchAlgorithmException {
        return userService.createUser(userDto,userDto.getPassword());
    }
}
