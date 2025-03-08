package itu.ko_working_api.controller;

import itu.ko_working_api.dto.upload.EspaceUpload;
import itu.ko_working_api.service.CsvService;
import itu.ko_working_api.service.EspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/upload-csv")
@RequiredArgsConstructor
public class FileUploadController {

    private final CsvService csvService;
    private final EspaceService espaceService;

    @PostMapping("/espace")
    public ResponseEntity<String> upEspace(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }

        try {
            List<EspaceUpload> r = csvService.parseEspaceUpload(file);
            espaceService.saveAsEntity(r);

            return ResponseEntity.ok("Tonga tsara aty amn Spring");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Misy olana kely");
        }
    }
}
