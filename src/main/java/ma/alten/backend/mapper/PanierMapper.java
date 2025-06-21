package ma.alten.backend.mapper;

import ma.alten.backend.domain.Panier;
import ma.alten.backend.dto.PanierDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,uses = {PanierItemMapper.class, UserMapper.class})
public interface PanierMapper {
    Panier toPanier(PanierDto panierDto);
    PanierDto toPanierDto(Panier panier);
}
