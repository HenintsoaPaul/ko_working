package itu.ko_working_api.dto.upload;

import itu.ko_working_api.dto.api.ApiResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class CsvOkResponse extends ApiResponse<String> {

    public CsvOkResponse(int nbRows) {
        super(
                HttpStatus.OK.value(),
                "Import csv ok",
                "Import de " + nbRows + " row(s)"
        );
        // Timestamp is already set in parent constructor
    }
}
