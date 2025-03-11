package itu.ko_working_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "paiement")
public class Paiement {
    @Id
    @Column(name = "id_paiement", nullable = false, length = 50)
    private String idPaiement;

    @Column(name = "date_paiement")
    private LocalDate datePaiement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_status_paiement", nullable = false)
    private StatusPaiement statusPaiement;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

}