package ma.alten.backend.dto.propertiesConfig;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import ma.alten.backend.constantes.Validation;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Data
@Component
@Validated
@ConfigurationProperties(prefix = "app")
public class CustomProperties {

    private final Jwt jwt = new Jwt();
    private final Frontend frontend = new Frontend();

    @Data
    public static class Jwt {
        @NotBlank(message = Validation.BLANK)
        private String secret;
    }

    @Data
    public static class Frontend {
        @NotBlank(message = Validation.BLANK)
        private String url;
    }
}