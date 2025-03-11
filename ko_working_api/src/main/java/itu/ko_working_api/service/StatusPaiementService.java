package itu.ko_working_api.service;

import itu.ko_working_api.entity.StatusPaiement;
import itu.ko_working_api.repository.StatusPaiementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatusPaiementService {

    private final StatusPaiementRepository statusPaiementRepository;

    @Cacheable("status_paiement")
    public List<StatusPaiement> findAll() {
        return statusPaiementRepository.findAll();
    }

    @Cacheable("status_en_attente")
    public StatusPaiement findStatusEnAttente() {
        String idAPayer = "ST_PAY0001";
        return statusPaiementRepository.findById(idAPayer)
                .orElseThrow(() -> new RuntimeException("Row status_paiement introuvable"));
    }

    @Cacheable("status_valide")
    public StatusPaiement findStatusValide() {
        String idAPayer = "ST_PAY0002";
        return statusPaiementRepository.findById(idAPayer)
                .orElseThrow(() -> new RuntimeException("Row status_paiement introuvable"));
    }
}
