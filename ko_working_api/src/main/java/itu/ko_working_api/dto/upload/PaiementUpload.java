package itu.ko_working_api.dto.upload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaiementUpload {
    @NotBlank(message = "'ref_paiement' cannot be blank")
    private String ref_paiement;

    @NotBlank(message = "'ref' cannot be blank")
    private String ref;

    // regex pour une date : dd/mm/yyyy
    @NotBlank(message = "'date' cannot be blank")
    public String date;
}
