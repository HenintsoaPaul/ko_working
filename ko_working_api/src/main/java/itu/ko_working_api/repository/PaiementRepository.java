package itu.ko_working_api.repository;

import itu.ko_working_api.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiementRepository extends JpaRepository<Paiement, String> {
    @Query("select generate_paiement_seq()")
    String generateId();
}
