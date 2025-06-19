package ma.alten.backend.dto.exception;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ErrorConstraintValidationResponse extends ErrorResponse {
    Map<String, String> Invalid_fields = new HashMap<String, String>();

    public ErrorConstraintValidationResponse() {
        super();
    }

    public ErrorConstraintValidationResponse(String methodArgumentsValidationError, String s, String requestURI, LocalDateTime now, Map<String, String> Invalid_fields) {
        super(methodArgumentsValidationError, s, requestURI, now);
        this.Invalid_fields = Invalid_fields;
    }
}
