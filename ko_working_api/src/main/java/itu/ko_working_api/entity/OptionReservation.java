package itu.ko_working_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
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

    public OptionReservation(Reservation reservation, PrixOption prixOption) {
        OptionReservationId id = new OptionReservationId();
        id.setIdReservation(reservation.getIdReservation());
        id.setIdPrixOption(prixOption.getIdPrixOption());
        this.setId(id);

        this.setReservation(reservation);
        this.setPrixOption(prixOption);
    }
}