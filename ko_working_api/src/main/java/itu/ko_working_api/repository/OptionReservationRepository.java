package itu.ko_working_api.repository;

import itu.ko_working_api.entity.OptionReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionReservationRepository extends JpaRepository<OptionReservation, String> {
    @Query("select generate_option_reservation_seq()")
    String generateId();
}
