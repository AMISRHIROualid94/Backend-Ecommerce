package ma.alten.backend.user.services.impl;

import ma.alten.backend.exception.BadRequestException;
import ma.alten.backend.user.dto.UserDto;
import ma.alten.backend.user.entity.UserEntity;
import ma.alten.backend.user.repository.UserRepo;
import ma.alten.backend.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserEntity searchByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserDto createUser(UserDto userDto, String password) throws NoSuchAlgorithmException {
        var user = convertToEntity(userDto);
        if (password.isBlank()) throw new IllegalArgumentException("Password is required.");
        var existsEmail = userRepo.selectExistsEmail(user.getEmail());
        if (existsEmail) throw new BadRequestException("Email " + user.getEmail() + " taken");
        byte[] salt = createSalt();
        byte[] hashedPassword = createPasswordHash(password, salt);
        user.setStoredSalt(salt);
        user.setStoredHash(hashedPassword);
        userRepo.save(user);
        return convertToDto(user);
    }

    @Override
    public UserEntity convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto,UserEntity.class);
    }

    @Override
    public UserDto convertToDto(UserEntity userEntity) {
        return modelMapper.map(userEntity,UserDto.class);
    }
    private byte[] createSalt() {
        var random = new SecureRandom();
        var salt = new byte[128];
        random.nextBytes(salt);
        return salt;
    }
    private byte[] createPasswordHash(String password, byte[] salt)
            throws NoSuchAlgorithmException {
        var md = MessageDigest.getInstance("SHA-512");
        md.update(salt);
        return md.digest(password.getBytes(StandardCharsets.UTF_8));
    }
}
