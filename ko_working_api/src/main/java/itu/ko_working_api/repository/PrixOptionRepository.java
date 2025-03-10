package itu.ko_working_api.repository;

import itu.ko_working_api.entity.PrixOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrixOptionRepository extends JpaRepository<PrixOption, String> {
    @Query("select generate_prix_option_seq()")
    String generateId();
}
