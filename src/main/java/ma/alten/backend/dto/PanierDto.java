package ma.alten.backend.dto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PanierDto {
    private Long id;
    private String userEmail;
    private List<PanierItemDto> items;
}

