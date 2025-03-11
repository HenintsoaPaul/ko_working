package itu.ko_working_api.service;

import itu.ko_working_api.entity.OptionReservation;
import itu.ko_working_api.entity.OptionReservationId;
import itu.ko_working_api.repository.OptionReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionReservationService {

    private final OptionReservationRepository optionReservationRepository;

    // save
    @Transactional
    public OptionReservation save(String idPrixOption, String idReservation, OptionReservation optionReservation) {
        OptionReservationId id = new OptionReservationId();
        id.setIdReservation(idReservation);
        id.setIdPrixOption(idPrixOption);

        optionReservation.setId(id);
        return optionReservationRepository.save(optionReservation);
    }

    @Transactional
    public void saveBatch(List<OptionReservation> optionReservations) {
        optionReservationRepository.saveAll(optionReservations);
    }
}
