package itu.ko_working_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;import org.hibernate.Hibernate;

import java.util.Objects;

@Data
@Embeddable
public class OptionReservationId implements java.io.Serializable {
    private static final long serialVersionUID = 147906567499359886L;
    @Column(name = "id_reservation", nullable = false, length = 50)
    private String idReservation;

    @Column(name = "id_prix_option", nullable = false, length = 50)
    private String idPrixOption;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OptionReservationId entity = (OptionReservationId) o;
        return Objects.equals(this.idReservation, entity.idReservation) &&
                Objects.equals(this.idPrixOption, entity.idPrixOption);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReservation, idPrixOption);
    }

}