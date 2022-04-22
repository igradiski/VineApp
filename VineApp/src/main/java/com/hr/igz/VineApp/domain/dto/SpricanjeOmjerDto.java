package com.hr.igz.VineApp.domain.dto;

public record SpricanjeOmjerDto (Double myDose,Double myDoseOn100, Double Dose,Double DoseOn100,Double percentage) {

    public SpricanjeOmjerDto(Double myDose, Double myDoseOn100, Double Dose, Double DoseOn100, Double percentage) {
        this.myDose = myDose;
        this.myDoseOn100 = myDoseOn100;
        this.Dose = Dose;
        this.DoseOn100 = DoseOn100;
        this.percentage = percentage;
    }
}
