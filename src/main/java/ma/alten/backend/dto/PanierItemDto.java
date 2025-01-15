package ma.alten.backend.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PanierItemDto {
    private String productName;
    private int quantity;

}
