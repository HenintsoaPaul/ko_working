package itu.ko_working_api.repository;

import itu.ko_working_api.entity.PrixHeureEspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PrixHeureEspaceRepository extends JpaRepository<PrixHeureEspace, String> {
    @Query("select generate_prix_heure_espace_seq()")
    String generateId();
}
