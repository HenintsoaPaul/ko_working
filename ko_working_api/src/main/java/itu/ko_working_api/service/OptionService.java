package itu.ko_working_api.service;

import itu.ko_working_api.dto.upload.OptionUpload;
import itu.ko_working_api.entity.Option;
import itu.ko_working_api.entity.PrixOption;
import itu.ko_working_api.repository.OptionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OptionService {

    private final GenericCsvService<OptionUpload> csvService;
    private final OptionRepository espaceRepository;
    private final PrixOptionService prixOptionService;

    @Transactional
    public Option save(Option espace) {
        String nextId = this.espaceRepository.generateId();
        espace.setIdOption(nextId);

        return this.espaceRepository.save(espace);
    }

    public List<OptionUpload> parseUpload(MultipartFile file) throws IOException {
        return csvService.parseUpload(file, OptionUpload.class);
    }

    public void saveAsEntities(List<OptionUpload> uploads) {
        for (OptionUpload upload : uploads) {
            this.saveAsEntities(upload);
        }
    }

    public void saveAsEntities(OptionUpload upload) {
        Option espace = new Option();
        espace.setCode(upload.getCode());
        espace.setOption(upload.getOption());
        this.save(espace);

        PrixOption prixOption = new PrixOption();
        prixOption.setOption(espace);
        prixOption.setVal(upload.getPrix());
        prixOption.setDateModification(null);
        prixOptionService.save(prixOption);
    }
}
