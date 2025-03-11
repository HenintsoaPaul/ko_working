package itu.ko_working_api.repository;

import itu.ko_working_api.entity.StatusPaiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPaiementRepository extends JpaRepository<StatusPaiement, String> {
}
