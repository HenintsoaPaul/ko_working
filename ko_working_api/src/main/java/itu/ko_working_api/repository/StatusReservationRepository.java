package itu.ko_working_api.repository;

import itu.ko_working_api.entity.StatusReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusReservationRepository extends JpaRepository<StatusReservation, String> {
}
