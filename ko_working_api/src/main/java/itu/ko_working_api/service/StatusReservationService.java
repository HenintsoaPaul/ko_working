package itu.ko_working_api.service;

import itu.ko_working_api.entity.StatusReservation;
import itu.ko_working_api.repository.StatusReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusReservationService {

    private final StatusReservationRepository statusReservationRepository;

    @Cacheable("status_reservation")
    public List<StatusReservation> findAll() {
        return statusReservationRepository.findAll();
    }

    @Cacheable("status_a_payer")
    public StatusReservation findStatusAPayer() {
        String idAPayer = "ST_RES0002";
        return statusReservationRepository.findById(idAPayer)
                .orElseThrow(() -> new RuntimeException("Row status_reservation introuvable"));
    }
}
