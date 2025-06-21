package ma.alten.backend.service;

import ma.alten.backend.exception.AuthenticationException;
import ma.alten.backend.dto.auth.UserDto;
import ma.alten.backend.domain.UserEntity;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    UserEntity searchByEmail(String email);
    UserDto createUser(UserDto userDto,String password) throws NoSuchAlgorithmException, AuthenticationException;
}
