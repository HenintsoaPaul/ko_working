package itu.ko_working_api.repository;

import itu.ko_working_api.entity.Option;
import itu.ko_working_api.entity.PrixOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrixOptionRepository extends JpaRepository<PrixOption, String> {
    @Query("select generate_prix_option_seq()")
    String generateId();

    @Query("select po from PrixOption po where po.option = :option order by po.idPrixOption desc limit 1")
    Optional<PrixOption> findLastByOption(Option option);
}
