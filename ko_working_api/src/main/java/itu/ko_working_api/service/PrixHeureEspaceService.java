package itu.ko_working_api.service;

import itu.ko_working_api.entity.PrixHeureEspace;
import itu.ko_working_api.repository.PrixHeureEspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrixHeureEspaceService {

    private final PrixHeureEspaceRepository prixHeureEspaceRepository;

    public PrixHeureEspace save(PrixHeureEspace prixHeureEspace) {
        String nextId = this.prixHeureEspaceRepository.generateId();
        prixHeureEspace.setIdPrixHeureEspace(nextId);

        return this.prixHeureEspaceRepository.save(prixHeureEspace);
    }
}
