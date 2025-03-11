package itu.ko_working_api.service;

import itu.ko_working_api.entity.Option;
import itu.ko_working_api.entity.PrixOption;
import itu.ko_working_api.repository.PrixOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrixOptionService {

    private final PrixOptionRepository prixOptionRepository;

    public PrixOption save(PrixOption prixOption) {
        String nextId = this.prixOptionRepository.generateId();
        prixOption.setIdPrixOption(nextId);

        return this.prixOptionRepository.save(prixOption);
    }

    // find
    public Optional<PrixOption> findLastByOption(Option option) {
        return prixOptionRepository.findLastByOption(option);
    }
}
