package ma.alten.backend.dto;

import java.util.List;

public record PanierDto(
        Long id,
        String userEmail,
        List<PanierItemDto> items) {
}

