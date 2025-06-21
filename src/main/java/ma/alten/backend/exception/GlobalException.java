package ma.alten.backend.exception;

import jakarta.servlet.http.HttpServletRequest;
import ma.alten.backend.constantes.Constante;
import ma.alten.backend.constantes.Validation;
import ma.alten.backend.dto.exception.ErrorConstraintValidationResponse;
import ma.alten.backend.dto.exception.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse("INTERNAL_SERVER_ERROR", Constante.ERRORPREFIX + "Unexpected error occurred: " + ex.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> Invalid_fields = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> Invalid_fields.put(error.getField(), error.getDefaultMessage()));
        ErrorResponse response = new ErrorConstraintValidationResponse("METHOD_ARGUMENTS_VALIDATION_ERROR", Constante.ERRORPREFIX, request.getRequestURI(), LocalDateTime.now(),Invalid_fields);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpServletRequest request) {
        Throwable cause = ex.getMostSpecificCause();
        if (cause instanceof DateTimeParseException) {
            Map<String, String> errors = new HashMap<>();
            errors.put("dateTime", Validation.DATETIME_PATTERN);

            ErrorResponse response = new ErrorConstraintValidationResponse("DATETIME_PARSE_ERROR", Constante.ERRORPREFIX + errors.get("dateTime"), request.getRequestURI(), LocalDateTime.now(), errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Map<String, String> fallbackError = new HashMap<>();
        fallbackError.put("request", Validation.REQUEST_DATA_FORMAT_ERROR);
        ErrorResponse fallbackResponse = new ErrorConstraintValidationResponse("JSON_PARSE_ERROR", Constante.ERRORPREFIX + fallbackError.get("request"), request.getRequestURI(), LocalDateTime.now(), fallbackError);
        return new ResponseEntity<>(fallbackResponse, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InvalidCredentielException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentielException(InvalidCredentielException ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse("FORBIDDEN", Constante.ERRORPREFIX + ex.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse("FORBIDDEN", Constante.ERRORPREFIX + ex.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse("UNAUTHORIZED", Constante.ERRORPREFIX + ex.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex, HttpServletRequest request) {
        ErrorResponse response = new ErrorResponse("BAD_REQUEST", Constante.ERRORPREFIX + ex.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, HttpServletRequest request){
        ErrorResponse response = new ErrorResponse("NOT_FOUND", Constante.ERRORPREFIX + ex.getMessage(), request.getRequestURI(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
