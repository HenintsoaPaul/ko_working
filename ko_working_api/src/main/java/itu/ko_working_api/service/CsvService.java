package itu.ko_working_api.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import itu.ko_working_api.dto.upload.EspaceUpload;
import itu.ko_working_api.dto.upload.OptionUpload;
import itu.ko_working_api.exception.CsvValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CsvService {

    private final Validator validator;

    private void validateBatch(List<EspaceUpload> rows) {
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            EspaceUpload row = rows.get(i);
            Set<ConstraintViolation<EspaceUpload>> violations = validator.validate(row);
            for (ConstraintViolation<EspaceUpload> violation : violations) {
                errors.add("Row " + (i + 1) + ": " + violation.getMessage());
            }
        }

        if (!errors.isEmpty()) {
            throw new CsvValidationException("CSV validation failed", errors);
        }
    }

    public List<EspaceUpload> parseEspaceUpload(MultipartFile file) throws IOException {
        try (
                InputStreamReader isr = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        ) {
            CsvToBean<EspaceUpload> csvToBean = new CsvToBeanBuilder<EspaceUpload>(br)
                    .withType(EspaceUpload.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<EspaceUpload> espaceUploads = csvToBean.parse();

            validateBatch(espaceUploads);

            return espaceUploads;
        }
    }

    public List<OptionUpload> parseOptionUpload(MultipartFile file) throws IOException {
        try (
                InputStreamReader isr = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        ) {
            CsvToBean<OptionUpload> csvToBean = new CsvToBeanBuilder<OptionUpload>(br)
                    .withType(OptionUpload.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        }
    }
}
