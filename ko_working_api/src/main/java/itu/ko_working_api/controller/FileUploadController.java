package itu.ko_working_api.controller;

import itu.ko_working_api.dto.api.ApiResponse;
import itu.ko_working_api.dto.upload.*;
import itu.ko_working_api.service.EspaceService;
import itu.ko_working_api.service.OptionService;
import itu.ko_working_api.service.PaiementService;
import itu.ko_working_api.service.ReservationService;
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

    private final EspaceService espaceService;
    private final OptionService optionService;
    private final ReservationService reservationService;
    private final PaiementService paiementService;

    @PostMapping("/espace")
    public ResponseEntity<ApiResponse<?>> upEspace(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        List<EspaceUpload> r = espaceService.parseUpload(file);
        espaceService.saveAsEntities(r);

        return ResponseEntity.ok(new CsvOkResponse(r.size()));

    }

    @PostMapping("/option")
    public ResponseEntity<ApiResponse<?>> upOption(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        List<OptionUpload> r = optionService.parseUpload(file);
        optionService.saveAsEntities(r);

        return ResponseEntity.ok(new CsvOkResponse(r.size()));
    }

    @PostMapping("/reservation")
    public ResponseEntity<ApiResponse<?>> upReservation(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        List<ReservationUpload> r = reservationService.parseUpload(file);
        reservationService.saveAsEntities(r);

        return ResponseEntity.ok(new CsvOkResponse(r.size()));
    }

    @PostMapping("/paiement")
    public ResponseEntity<ApiResponse<?>> upPaiement(
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        List<PaiementUpload> r = paiementService.parseUpload(file);
        paiementService.saveAsEntities(r);

        return ResponseEntity.ok(new CsvOkResponse(r.size()));
    }
}
