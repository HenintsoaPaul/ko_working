package itu.ko_working_api.configs;

import itu.ko_working_api.dto.api.ApiResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Erreur de validation level controller
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ApiResponse<HashMap<String, String>>> handleValidation(MethodArgumentNotValidException exception) {
        HashMap<String, String> mapErrors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach(
                err -> mapErrors.put(err.getDefaultMessage(), err.getDefaultMessage())
        );

        ApiResponse<HashMap<String, String>> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Erreur de validation level controller bro",
                mapErrors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Erreur de validation level service
    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ApiResponse<HashMap<String, String>>> handleValidation(ConstraintViolationException exception) {
        HashMap<String, String> mapErrors = new HashMap<>();

        exception.getConstraintViolations().forEach(violation -> {
            mapErrors.put(violation.getPropertyPath().toString(), violation.getMessage());
        });

        ApiResponse<HashMap<String, String>> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Erreur de validation level service bro",
                mapErrors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // ex: file upload
    @ExceptionHandler(value = {IOException.class})
    public ResponseEntity<ApiResponse<String>> handleIOException(IOException exception) {
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erreur I/O bro",
                exception.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ApiResponse<String>> handleException(Exception exception) {
        ApiResponse<String> response = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erreur de serveur bro",
                exception.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
