package com.hr.igz.VineApp.domain.dto;

import javax.validation.constraints.NotBlank;
import java.time.Instant;


public record SredstvoDto(
        @NotBlank(message = "Name is mandatory!") String name,
        @NotBlank(message = "Description is mandatory!") String description,
        String composition,
        String group,
        String formulation,
        String typeOfAction,
        String usage,
        Float concentration,
        Float dosageOn100,
        Integer waiting,
        Long typeOfMedium,
        Instant date,
        String nameOfTipSredstva,
        Long tipSredstvaId,
        String base64,
        String picture_name,
        Long id) {
    public SredstvoDto(
            @NotBlank(message = "Name is mandatory!") String name,
            @NotBlank(message = "Description is mandatory!") String description,
            String composition,
            String group,
            String formulation,
            String typeOfAction,
            String usage,
            Float concentration,
            Float dosageOn100,
            Integer waiting,
            Long typeOfMedium,
            Instant date,
            String nameOfTipSredstva,
            Long tipSredstvaId,
            String base64,
            String picture_name,
            Long id) {
        this.name = name;
        this.description = description;
        this.composition = composition;
        this.group = group;
        this.formulation = formulation;
        this.typeOfAction = typeOfAction;
        this.usage = usage;
        this.concentration = concentration;
        this.dosageOn100 = dosageOn100;
        this.waiting = waiting;
        this.typeOfMedium = typeOfMedium;
        this.date = date;
        this.nameOfTipSredstva = nameOfTipSredstva;
        this.tipSredstvaId = tipSredstvaId;
        this.base64 = base64;
        this.picture_name = picture_name;
        this.id = id;
    }
}
