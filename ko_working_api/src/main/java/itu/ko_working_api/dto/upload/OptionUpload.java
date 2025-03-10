package itu.ko_working_api.dto.upload;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OptionUpload {
    private String code;
    private String option;
    public double prix;
}
