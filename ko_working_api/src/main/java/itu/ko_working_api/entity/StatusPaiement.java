package itu.ko_working_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "status_paiement")
public class StatusPaiement {
    @Id
    @Column(name = "id_status_paiement", nullable = false, length = 50)
    private String idStatusPaiement;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

}