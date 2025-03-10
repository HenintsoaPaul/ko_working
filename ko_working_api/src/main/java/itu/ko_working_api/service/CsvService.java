package itu.ko_working_api.service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import itu.ko_working_api.dto.upload.EspaceUpload;
import itu.ko_working_api.dto.upload.OptionUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CsvService {

    public List<EspaceUpload> parseEspaceUpload(MultipartFile file) throws IOException {
        try (
                InputStreamReader isr = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
        ) {
            CsvToBean<EspaceUpload> csvToBean = new CsvToBeanBuilder<EspaceUpload>(br)
                    .withType(EspaceUpload.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
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
