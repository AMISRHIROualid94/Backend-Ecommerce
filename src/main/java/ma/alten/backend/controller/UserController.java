package ma.alten.backend.controller;

import jakarta.validation.Valid;
import ma.alten.backend.exception.AuthenticationException;
import ma.alten.backend.dto.auth.UserDto;
import ma.alten.backend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/account")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) throws NoSuchAlgorithmException, AuthenticationException {
        return ResponseEntity.ok(userService.createUser(userDto,userDto.password()));
    }
}
