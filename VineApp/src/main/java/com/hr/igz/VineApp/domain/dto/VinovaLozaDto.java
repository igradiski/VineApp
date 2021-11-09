package com.hr.igz.VineApp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.Instant;


@Data
@NoArgsConstructor
public class VinovaLozaDto {

    private Long id;

    @NotBlank(message = "Name is mandatory!")
    private String name;

    @NotBlank(message = "Description is mandatory!")
    private String description;

    private Instant date;

    private String base64;

    private String picture_name;
}
