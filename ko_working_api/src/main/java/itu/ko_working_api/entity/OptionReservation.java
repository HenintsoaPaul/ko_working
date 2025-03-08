package itu.ko_working_api.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "option_reservation")
public class OptionReservation {
    @EmbeddedId
    private OptionReservationId id;

    @MapsId("idReservation")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_reservation", nullable = false)
    private Reservation reservation;

    @MapsId("idPrixOption")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_prix_option", nullable = false)
    private PrixOption prixOption;

}