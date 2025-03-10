package itu.ko_working_api.exception;

import itu.ko_working_api.dto.api.ApiResponse;
import itu.ko_working_api.dto.upload.CsvNoResponse;
import itu.ko_working_api.dto.upload.UploadResponseBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

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

    @ExceptionHandler(CsvValidationException.class)
    public ResponseEntity<CsvNoResponse> handleCsvValidation(CsvValidationException ex) {
        return new ResponseEntity<>(new CsvNoResponse(ex), HttpStatus.BAD_REQUEST);
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
