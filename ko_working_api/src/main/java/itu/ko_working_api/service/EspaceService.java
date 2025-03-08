package itu.ko_working_api.service;

import itu.ko_working_api.dto.upload.EspaceUpload;
import itu.ko_working_api.entity.Espace;
import itu.ko_working_api.entity.PrixHeureEspace;
import itu.ko_working_api.repository.EspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspaceService {

    private final EspaceRepository espaceRepository;
    private final PrixHeureEspaceService prixHeureEspaceService;

    public Espace save(Espace espace) {
        String nextId = this.espaceRepository.generateId();
        espace.setIdEspace(nextId);

        return this.espaceRepository.save(espace);
    }

    public void saveAsEntities(List<EspaceUpload> uploads) {
        for (EspaceUpload upload : uploads) {
            this.saveAsEntities(upload);
        }
    }

    public void saveAsEntities(EspaceUpload upload) {
        Espace espace = new Espace();
        espace.setNom(upload.getNom());
        this.save(espace);

        PrixHeureEspace prixHeureEspace = new PrixHeureEspace();
        prixHeureEspace.setEspace(espace);
        prixHeureEspace.setVal(upload.getPrix_heure());
        prixHeureEspace.setDateModification(null);
        prixHeureEspaceService.save(prixHeureEspace);
    }
}
