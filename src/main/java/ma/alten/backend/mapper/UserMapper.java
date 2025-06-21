package ma.alten.backend.mapper;

import ma.alten.backend.dto.auth.UserDto;
import ma.alten.backend.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserDto toUserDto(UserEntity userEntity);
    UserEntity toEntity(UserDto userDto);
}
