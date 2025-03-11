package itu.ko_working_api.repository;

import itu.ko_working_api.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    @Query("select generate_reservation_seq()")
    String generateId();
}
