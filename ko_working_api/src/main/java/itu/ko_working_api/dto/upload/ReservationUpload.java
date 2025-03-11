package itu.ko_working_api.dto.upload;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ReservationUpload {
    @NotBlank(message = "'ref' cannot be blank")
    private String ref;

    @NotBlank(message = "'espace' cannot be blank")
    private String espace;

    @NotBlank(message = "'client' cannot be blank")
    @Size(min = 10, max = 10, message = "'client' must be 10 digits" )
    private String client;

    // regex pour une date : dd/mm/yyyy
    @NotBlank(message = "'date' cannot be blank")
    private String date;

    // regex pour une date : hh:mm
    @NotBlank(message = "'heure_debut' cannot be blank")
    private String heure_debut;

    @Min(value = 1, message = "Min value for duree is 1")
    @Max(value = 4, message = "Max value for duree is 4")
    private int duree;

    // if many: "opt1, opt2, opt3" | else: "opt1"
    @NotBlank(message = "'option' cannot be blank")
    private String option;
}
