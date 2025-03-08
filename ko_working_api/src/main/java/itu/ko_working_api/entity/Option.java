package itu.ko_working_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "option")
public class Option {
    @Id
    @Column(name = "id_option", nullable = false, length = 50)
    private String idOption;

    @Column(name = "code", nullable = false, length = 50)
    private String code;

    @Column(name = "option", nullable = false, length = 50)
    private String option;

}