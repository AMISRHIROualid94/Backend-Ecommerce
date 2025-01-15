package ma.alten.backend.user.services;

import ma.alten.backend.user.dto.UserDto;
import ma.alten.backend.user.entity.UserEntity;

import java.security.NoSuchAlgorithmException;

public interface UserService {
    UserEntity searchByEmail(String email);
    UserDto createUser(UserDto userDto,String password) throws NoSuchAlgorithmException;
    UserEntity convertToEntity(UserDto userDto);
    UserDto convertToDto(UserEntity userEntity);
}
