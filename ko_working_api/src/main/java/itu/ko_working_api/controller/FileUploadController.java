package itu.ko_working_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/upload-csv")
public class FileUploadController {

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }

        try {
            String fileName = file.getOriginalFilename();

//            // save le fichier
            byte[] bytes = file.getBytes();
//            Path path = Paths.get("D:\\ITU\\Semester6\\ko_working\\ko_working_api\\database" + fileName);
//            Files.write(path, bytes);

            return ResponseEntity.ok("Uploaded Successfully");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error while uploading file");
        }
    }
}
