package itu.ko_working_api.dto.upload;

import itu.ko_working_api.exception.CsvValidationException;

public class UploadResponseBuilder {

    public CsvOkResponse buildCsvOkResponse(int nbRows) {
        return new CsvOkResponse(nbRows);
    }

    public CsvNoResponse buildCsvNoResponse(CsvValidationException e) {
        return new CsvNoResponse(e);
    }
}
