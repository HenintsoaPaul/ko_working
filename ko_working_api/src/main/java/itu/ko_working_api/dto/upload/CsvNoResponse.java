package itu.ko_working_api.dto.upload;

import itu.ko_working_api.dto.api.ApiResponse;
import itu.ko_working_api.exception.CsvValidationException;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CsvNoResponse extends ApiResponse<List<String>> {

    public CsvNoResponse(CsvValidationException e) {
        super(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                e.getErrors()
        );
        // Timestamp is already set in parent constructor
    }

    public CsvNoResponse(String message) {
        super(
                HttpStatus.BAD_REQUEST.value(),
                message,
                null
        );
        // Timestamp is already set in parent constructor
    }
}
