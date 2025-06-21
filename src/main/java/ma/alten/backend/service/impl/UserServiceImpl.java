package ma.alten.backend.service.impl;

import lombok.RequiredArgsConstructor;
import ma.alten.backend.constantes.ExceptionConst;
import ma.alten.backend.domain.UserEntity;
import ma.alten.backend.dto.auth.UserDto;
import ma.alten.backend.exception.AuthenticationException;
import ma.alten.backend.exception.BadRequestException;
import ma.alten.backend.exception.NotFoundException;
import ma.alten.backend.mapper.UserMapper;
import ma.alten.backend.repo.UserRepo;
import ma.alten.backend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity searchByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(()-> new NotFoundException(String.format(ExceptionConst.USER_NOT_FOUND, email)));
    }

    @Override
    public UserDto createUser(UserDto userDto, String password) throws NoSuchAlgorithmException, AuthenticationException {
        UserEntity user = userMapper.toEntity(userDto);
        if (password.isBlank()) throw new AuthenticationException(ExceptionConst.PASSWORD_REQUIRED);
        var existsEmail = userRepo.selectExistsEmail(user.getEmail());
        if (existsEmail) throw new BadRequestException(String.format(ExceptionConst.EMAIL_TAKEN, user.getEmail()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toUserDto(userRepo.save(user));
    }
}
