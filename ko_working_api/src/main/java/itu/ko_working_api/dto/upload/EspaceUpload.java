package itu.ko_working_api.dto.upload;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EspaceUpload {
    private String nom;
    public double prix_heure;
}
