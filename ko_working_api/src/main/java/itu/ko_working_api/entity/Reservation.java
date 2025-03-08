package itu.ko_working_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @Column(name = "id_reservation", nullable = false, length = 50)
    private String idReservation;

    @Column(name = "date_reservation", nullable = false)
    private LocalDate dateReservation;

    @Column(name = "heure_debut", nullable = false)
    private LocalTime heureDebut;

    @Column(name = "heure_fin", nullable = false)
    private LocalTime heureFin;

    @Column(name = "duree", nullable = false)
    private Integer duree;

    @Column(name = "montant", nullable = false)
    private Double montant;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_prix_heure_espace", nullable = false)
    private PrixHeureEspace prixHeureEspace;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_status_reservation", nullable = false)
    private StatusReservation statusReservation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_client", nullable = false)
    private Client client;

}