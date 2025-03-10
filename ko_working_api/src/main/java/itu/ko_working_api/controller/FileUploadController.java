package itu.ko_working_api.controller;

import itu.ko_working_api.dto.upload.EspaceUpload;
import itu.ko_working_api.dto.upload.OptionUpload;
import itu.ko_working_api.service.CsvService;
import itu.ko_working_api.service.EspaceService;
import itu.ko_working_api.service.OptionService;
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
    private final OptionService optionService;

    @PostMapping("/espace")
    public ResponseEntity<String> upEspace(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }

        List<EspaceUpload> r = csvService.parseEspaceUpload(file);
        espaceService.saveAsEntities(r);

        return ResponseEntity.ok("Tonga tsara aty amn Spring");

    }

    @PostMapping("/option")
    public ResponseEntity<String> upOption(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }

        List<OptionUpload> r = csvService.parseOptionUpload(file);
        optionService.saveAsEntities(r);

        return ResponseEntity.ok("Tonga tsara aty amn Spring");
    }
}
