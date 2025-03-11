package itu.ko_working_api.service;

import itu.ko_working_api.dto.upload.ReservationUpload;
import itu.ko_working_api.entity.*;
import itu.ko_working_api.exception.CsvValidationException;
import itu.ko_working_api.repository.ReservationRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final GenericCsvService<ReservationUpload> csvService;
    private final ReservationRepository reservationRepository;
    private final ClientService clientService;
    private final EspaceService espaceService;
    private final PrixHeureEspaceService prixHeureEspaceService;
    private final OptionService optionService;
    private final PrixOptionService prixOptionService;
    private final OptionReservationService optionReservationService;
    private final StatusReservationService statusReservationService;

    // save
    @Transactional
    public Reservation save(@Valid Reservation reservation) {
        return this.reservationRepository.save(reservation);
    }

    // find
    public Reservation findById(String id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation '" + id + "' not found"));
    }

    // csv
    @Transactional
    public List<ReservationUpload> parseUpload(MultipartFile file) throws IOException {
        return csvService.parseUpload(file, ReservationUpload.class);
    }

    @Transactional
    public void saveAsEntities(List<ReservationUpload> uploads) {
        List<Reservation> reservations = uploads.stream()
                .map(this::convertToEntity)
                .toList();
        reservationRepository.saveAll(reservations);

        List<OptionReservation> optionReservations = reservations.stream()
                .flatMap(res -> res.getOptionReservations().stream())
                .toList();
        optionReservationService.saveAll(optionReservations);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveAsEntity(ReservationUpload upload) {
        try {
            Reservation reservation = convertToEntity(upload);
            this.save(reservation);

            optionReservationService.saveAll(reservation.getOptionReservations());

            log.info("Réservation '{}' saved", reservation.getIdReservation());
        } catch (CsvValidationException e) {
            log.error("Erreur lors de l'importation d'une réservation : {}", e.getMessage());
            throw e;
        }
    }

    private Reservation convertToEntity(ReservationUpload upload) {
        Reservation reservation = new Reservation();
        // id_reservation <= ref
        reservation.setIdReservation(upload.getRef());

        // Récupération des entités liées
        reservation.setClient(parseClient(upload.getClient()));
        PrixHeureEspace phe = parseEspace(upload.getEspace());
        reservation.setPrixHeureEspace(phe);
        List<PrixOption> prixOptions = parseOption(upload.getOption());
        List<OptionReservation> optionReservations = parseOption(prixOptions, reservation);

        // Parsing des dates et heures
        reservation.setDateReservation(parseDate(upload.getDate()));
        reservation.setHeureDebut(parseHeure(upload.getHeure_debut()));
        reservation.setDuree(upload.getDuree());
        reservation.setHeureFin(reservation.getHeureDebut().plusHours(reservation.getDuree()));

        // Calcul du montant
        double montant = phe.getVal() + prixOptions.stream().mapToDouble(PrixOption::getVal).sum();
        reservation.setMontant(montant);

        // Status reservation
        StatusReservation sr = statusReservationService.findStatusAPayer();
        reservation.setStatusReservation(sr);

        // Associer les options à la réservation
        reservation.setOptionReservations(optionReservations);

        return reservation;
    }

    private LocalTime parseHeure(@NotBlank(message = "'heure_debut' cannot be blank") String heureDebut) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(heureDebut, formatter);
    }

    private LocalDate parseDate(@NotBlank(message = "'date' cannot be blank") String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(date, formatter);
    }

    private Client parseClient(String strClient) {
        Client client;
        Optional<Client> optClient = clientService.findByNumero(strClient);
        if (optClient.isEmpty()) {
            log.info("Client '{}' ne correspond a aucune ligne dans la table client!", strClient);

            Client newClient = new Client();
            newClient.setNumero(strClient);
            clientService.save(newClient);

            client = newClient;
        } else {
            client = optClient.get();
        }

        return client;
    }

    private PrixHeureEspace parseEspace(String strEspace) {
        Espace espace;
        Optional<Espace> optEspace = espaceService.findByNom(strEspace);

        if (optEspace.isEmpty()) {
            log.info("Espace '{}' ne correspond a aucune ligne dans la table espace!", strEspace);
            // ne peut pas creer des espaces a volonter car chaque espace doit avoir un prix
            throw new CsvValidationException("Espace '" + strEspace + "' ne correspond a aucune ligne dans la table espace! ", null);
        }

        espace = optEspace.get();
        return prixHeureEspaceService.findLastByEspace(espace)
                .orElseThrow(() -> new CsvValidationException("Espace inconnu", null));
    }

    private List<PrixOption> parseOption(String strOptions) {
        // parse options
        List<String> listOptions = new ArrayList<>();
        if (strOptions.contains(",")) {
            listOptions.addAll(Arrays.asList(strOptions.split(", ")));
        } else {
            listOptions.add(strOptions);
        }

        // fk options
        List<PrixOption> prixOptions = new ArrayList<>();
        for (String elmt : listOptions) {
            String strOpt = elmt.toUpperCase();

            Option option = optionService.findByCode(strOpt)
                    .orElseThrow(() -> new CsvValidationException("Option '" + strOpt + "' n'existe pas", null));

            prixOptionService.findLastByOption(option).ifPresent(prixOptions::add);
        }
        return prixOptions;
    }

    private List<OptionReservation> parseOption(List<PrixOption> prixOptions, Reservation reservation) {
        List<OptionReservation> optionReservations = new ArrayList<>();
        for (PrixOption po : prixOptions) {
            optionReservations.add(new OptionReservation(reservation, po));
        }
        return optionReservations;
    }
}
