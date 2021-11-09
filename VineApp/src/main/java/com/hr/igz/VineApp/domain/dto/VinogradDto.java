package com.hr.igz.VineApp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@NoArgsConstructor
public class VinogradDto {

    private Long id;

    @NotBlank(message = "Name is mandatory!")
    private String name;

    @NotBlank(message = "Adress is mandatory!")
    private String adress;

    @NotBlank(message = "Description is mandatory!")
    private String description;


    private Integer brojCokota;

    private Instant date;

}