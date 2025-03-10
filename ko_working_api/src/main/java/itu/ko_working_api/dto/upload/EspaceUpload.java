package itu.ko_working_api.dto.upload;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EspaceUpload {
    @NotBlank(message = "'nom' cannot be blank")
    private String nom;

    @DecimalMin(value = "0.0", inclusive = false, message = "'prix_heure' must be strictly sup to 0.0")
    public double prix_heure;
}
