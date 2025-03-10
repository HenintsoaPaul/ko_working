package itu.ko_working_api.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
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
public class GenericCsvService<T> {

    private final Validator validator;

    private void validateBatch(List<T> rows) {
        List<String> errors = new ArrayList<>();

        for (int i = 0; i < rows.size(); i++) {
            T row = rows.get(i);
            Set<ConstraintViolation<T>> violations = validator.validate(row);
            for (ConstraintViolation<T> violation : violations) {
                errors.add("Row " + (i + 1) + ": " + violation.getMessage());
            }
        }

        if (!errors.isEmpty()) {
            throw new CsvValidationException("CSV validation failed", errors);
        }
    }

    public List<T> parseUpload(MultipartFile file, Class<T> clazz) throws IOException {
        try (
                InputStreamReader isr = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr)
        ) {
            if (file.isEmpty()) {
                throw new CsvValidationException("File vide rangah", null);
            }

            CsvToBean<T> csvToBean = new CsvToBeanBuilder<T>(br)
                    .withType(clazz)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<T> uploads = csvToBean.parse();

            validateBatch(uploads);

            return uploads;
        }
    }
}
