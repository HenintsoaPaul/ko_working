package itu.ko_working_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "status_reservation")
public class StatusReservation {
    @Id
    @Column(name = "id_status_reservation", nullable = false, length = 50)
    private String idStatusReservation;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

}