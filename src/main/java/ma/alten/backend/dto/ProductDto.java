package ma.alten.backend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import ma.alten.backend.constantes.Constante;
import ma.alten.backend.constantes.Validation;
import ma.alten.backend.domain.InventoryStatus;

import java.time.LocalDateTime;

public record ProductDto(
        @NotBlank(message = Validation.BLANK)
        String code,

        @NotBlank(message = Validation.BLANK)
        String name,

        String description,
        String image,

        @NotBlank(message = Validation.BLANK)
        String category,

        @NotNull(message = Validation.BLANK)
        Double price,

        @NotNull(message = Validation.BLANK)
        @Min(value = 0, message = Validation.MIN)
        Integer quantity,

        String internalReference,

        @NotNull(message = Validation.BLANK)
        Integer shellId,

        @NotNull(message = Validation.BLANK)
        InventoryStatus inventoryStatus,

        @NotNull(message = Validation.BLANK)
        @Min(value = 0, message = Validation.MIN)
        @Max(value = 10, message = Validation.MAX)
        Integer rating,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constante.DATE_TIME_FORMAT)
        LocalDateTime createdAt,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constante.DATE_TIME_FORMAT)
        LocalDateTime updatedAt) {

}
