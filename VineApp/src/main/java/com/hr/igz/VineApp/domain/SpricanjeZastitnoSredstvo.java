package com.hr.igz.VineApp.domain;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="spricanje_has_zastitno_sredstvo")
@EntityListeners(AuditingEntityListener.class)
public class SpricanjeZastitnoSredstvo {

    private static final long serialVersionUID = 1L;

    public SpricanjeZastitnoSredstvo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_SPRICSRED")
    @SequenceGenerator(name="SEQ_SPRICSRED",allocationSize = 1)
    private Long id;

    private Double dosage;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="zastitno_sredstvo_id")
    private ZastitnoSredstvo zastitnoSredstvo;

    @ManyToOne(fetch=FetchType.LAZY)
    private Spricanje spricanje;

    private String remark;

    @Column(name = "user_dosage")
    private Double user_dosage;

    @CreatedDate
    private Instant date;

    @LastModifiedDate
    @Column(name = "updated_date")
    private Instant updatedDate;

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public Instant getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Instant updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDosage() {
        return dosage;
    }

    public void setDosage(Double dosage) {
        this.dosage = dosage;
    }

    public ZastitnoSredstvo getZastitnoSredstvo() {
        return zastitnoSredstvo;
    }

    public void setZastitnoSredstvo(ZastitnoSredstvo zastitnoSredstvo) {
        this.zastitnoSredstvo = zastitnoSredstvo;
    }

    public Spricanje getSpricanje() {
        return spricanje;
    }

    public void setSpricanje(Spricanje spricanje) {
        this.spricanje = spricanje;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getUser_dosage() {
        return user_dosage;
    }

    public void setUser_dosage(Double user_dosage) {
        this.user_dosage = user_dosage;
    }
}
