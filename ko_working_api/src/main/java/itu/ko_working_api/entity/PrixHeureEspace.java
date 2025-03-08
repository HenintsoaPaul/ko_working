package itu.ko_working_api.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prix_heure_espace")
public class PrixHeureEspace {
    @Id
    @Column(name = "id_prix_heure_espace", nullable = false, length = 50)
    private String idPrixHeureEspace;

    @Column(name = "val", nullable = false)
    private Double val;

    @Column(name = "date_modification")
    private LocalDate dateModification;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_espace", nullable = false)
    private Espace espace;

}