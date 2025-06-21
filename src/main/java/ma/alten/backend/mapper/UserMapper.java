package ma.alten.backend.mapper;

import ma.alten.backend.dto.auth.UserDto;
import ma.alten.backend.domain.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    @Mapping(target = "password",ignore = true)
    UserDto toUserDto(UserEntity userEntity);
    
    UserEntity toEntity(UserDto userDto);
}
