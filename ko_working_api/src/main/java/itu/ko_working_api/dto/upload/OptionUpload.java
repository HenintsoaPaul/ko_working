package itu.ko_working_api.dto.upload;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OptionUpload {
    @NotBlank(message = "'code' cannot be blank")
    private String code;

    @NotBlank(message = "'option' cannot be blank")
    private String option;

    @DecimalMin(value = "0.0", inclusive = false, message = "'prix' must be strictly sup to 0.0")
    public double prix;
}
