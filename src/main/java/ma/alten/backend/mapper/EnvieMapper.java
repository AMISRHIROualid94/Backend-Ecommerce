package ma.alten.backend.mapper;

import ma.alten.backend.domain.Envie;
import ma.alten.backend.dto.EnvieDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {ProductMapper.class})
public interface EnvieMapper {
    EnvieDto toEnvieDto(Envie envie);
}
