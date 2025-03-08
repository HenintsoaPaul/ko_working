package itu.ko_working_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "client")
public class Client {
    @Id
    @Column(name = "id_client", nullable = false, length = 50)
    private String idClient;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "numero", nullable = false, length = 50)
    private String numero;

}