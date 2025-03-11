package itu.ko_working_api.service;

import itu.ko_working_api.dto.upload.EspaceUpload;
import itu.ko_working_api.entity.Espace;
import itu.ko_working_api.entity.PrixHeureEspace;
import itu.ko_working_api.repository.EspaceRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EspaceService {

    private final GenericCsvService<EspaceUpload> csvService;
    private final EspaceRepository espaceRepository;
    private final PrixHeureEspaceService prixHeureEspaceService;

    @Transactional
    public Espace save(@Valid Espace espace) {
        String nextId = this.espaceRepository.generateId();
        espace.setIdEspace(nextId);

        return this.espaceRepository.save(espace);
    }

    public List<EspaceUpload> parseUpload(MultipartFile file) throws IOException {
        return csvService.parseUpload(file, EspaceUpload.class);
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

    // find
    public Optional<Espace> findByNom(String nom) {
        return espaceRepository.findByNom(nom);
    }
}
