package itu.ko_working_api.repository;

import itu.ko_working_api.entity.Espace;
import itu.ko_working_api.entity.PrixHeureEspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrixHeureEspaceRepository extends JpaRepository<PrixHeureEspace, String> {
    @Query("select generate_prix_heure_espace_seq()")
    String generateId();

    @Query("select phe from PrixHeureEspace phe where phe.espace = :espace order by phe.idPrixHeureEspace desc limit 1")
    Optional<PrixHeureEspace> findLastByEspace(Espace espace);
}
