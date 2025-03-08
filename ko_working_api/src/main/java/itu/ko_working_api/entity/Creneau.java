package itu.ko_working_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "creneau")
public class Creneau {
    @Id
    @Column(name = "id_creneau", nullable = false, length = 50)
    private String idCreneau;

    @Column(name = "date_creneau", nullable = false)
    private LocalDate dateCreneau;

    @Column(name = "heure_creneau", nullable = false)
    private LocalTime heureCreneau;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

}