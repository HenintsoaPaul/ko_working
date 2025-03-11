package itu.ko_working_api.service;

import itu.ko_working_api.dto.upload.PaiementUpload;
import itu.ko_working_api.entity.*;
import itu.ko_working_api.exception.CsvValidationException;
import itu.ko_working_api.repository.PaiementRepository;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaiementService {

    private final GenericCsvService<PaiementUpload> csvService;
    private final PaiementRepository paiementRepository;
    private final ReservationService reservationService;
    private final StatusPaiementService statusPaiementService;

    // save
    public Paiement save(Paiement paiement) {
        String nextId = paiementRepository.generateId();
        paiement.setIdPaiement(nextId);

        return paiementRepository.save(paiement);
    }

    public List<PaiementUpload> parseUpload(MultipartFile file) throws IOException {
        return csvService.parseUpload(file, PaiementUpload.class);
    }

    public void saveAsEntities(List<PaiementUpload> uploads) {
        List<Paiement> paiements = uploads.stream()
                .map(this::convertToEntity)
                .toList();
        paiementRepository.saveAll(paiements);
    }

    public void saveAsEntity(PaiementUpload upload) {
        try {
            Paiement paiement = convertToEntity(upload);
            this.save(paiement);

            log.info("Paiement '{}' saved", paiement.getIdPaiement());
        } catch (CsvValidationException e) {
            log.error("Erreur lors de l'importation d'une réservation : {}", e.getMessage());
            throw e;
        }
    }

    private Paiement convertToEntity(PaiementUpload upload) {
        Paiement paiement = new Paiement();
        paiement.setIdPaiement(upload.getRef_paiement());

        // Récupération des entités liées
        Reservation reservation = parseReservation(upload.getRef());
        paiement.setReservation(reservation);

        // Parsing des dates et heures
        paiement.setDatePaiement(parseDate(upload.getDate()));

        // Status paiement
        StatusPaiement sp;
        StatusReservation sr = reservation.getStatusReservation();
        if (Objects.equals(sr.getIdStatusReservation(), "ST_RES0002") || Objects.equals(sr.getIdStatusReservation(), "ST_RES0003")) {
            sp = statusPaiementService.findStatusEnAttente();
        } else {
            sp = statusPaiementService.findStatusValide();
        }
        paiement.setStatusPaiement(sp);

        return paiement;
    }

    private Reservation parseReservation(String strReservation) {
        return reservationService.findById(strReservation);
    }

    private LocalDate parseDate(@NotBlank(message = "'date' cannot be blank") String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }
}
