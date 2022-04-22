package com.hr.igz.VineApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name="VINOVALOZA")
@EntityListeners(AuditingEntityListener.class)
public class Vinovaloza {

    private static final long serialVersionUID = 1L;

    public Vinovaloza() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_VINOVALOZA")
    @SequenceGenerator(name="SEQ_VINOVALOZA",allocationSize = 1)
    private Long id;

    private String name;

    private String description;

    @CreatedDate
    private Instant date;

    @LastModifiedDate
    @Column(name = "updated_date")
    private Instant updatedDate;

    @OneToMany(mappedBy="vinovaloza")
    private Set<VinogradVinovaloza> vinogradVinovalozas;

    @JsonIgnore
    private byte[] picture;

    private String picture_name;

    private Integer approved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Set<VinogradVinovaloza> getVinogradHasVinovalozas() {
        return vinogradVinovalozas;
    }

    public void setVinogradHasVinovalozas(Set<VinogradVinovaloza> vinogradVinovalozas) {
        this.vinogradVinovalozas = vinogradVinovalozas;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public String getPicture_name() {
        return picture_name;
    }

    public void setPicture_name(String picture_name) {
        this.picture_name = picture_name;
    }

    public Integer getApproved() {
        return approved;
    }

    public void setApproved(Integer approved) {
        this.approved = approved;
    }
}
