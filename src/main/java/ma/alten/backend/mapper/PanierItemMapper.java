package ma.alten.backend.mapper;

import ma.alten.backend.domain.PanierItem;
import ma.alten.backend.dto.PanierItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = {ProductMapper.class})
public interface PanierItemMapper {
    PanierItem toPanierItem(PanierItemDto panierItemDto);
    PanierItemDto toPanierItemDto(PanierItem panierItem);
}
