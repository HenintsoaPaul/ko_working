package itu.ko_working_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "espace")
public class Espace {
    @Id
    @Column(name = "id_espace", nullable = false, length = 50)
    private String idEspace;

    @Column(name = "nom", nullable = false, length = 50)
    private String nom;

}