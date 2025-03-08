package itu.ko_working_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Data
@Entity
@Table(name = "paiment")
public class Paiment {
    @Id
    @Column(name = "id_paiment", nullable = false, length = 50)
    private String idPaiment;

    @Column(name = "date_paiement")
    private Instant datePaiement;

    @Column(name = "ref_paiement", nullable = false, length = 50)
    private String refPaiement;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_status_paiement", nullable = false)
    private StatusPaiement statusPaiement;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

}