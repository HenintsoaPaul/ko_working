package itu.ko_working_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prix_option")
public class PrixOption {
    @Id
    @Column(name = "id_prix_option", nullable = false, length = 50)
    private String idPrixOption;

    @Column(name = "val", nullable = false)
    private Double val;

    @Column(name = "date_modification")
    private LocalDate dateModification;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_option", nullable = false)
    private Option option;

}