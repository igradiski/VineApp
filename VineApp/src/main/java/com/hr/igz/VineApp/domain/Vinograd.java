package com.hr.igz.VineApp.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;


@Entity(name="Vinograd")
@Table(name="VINOGRAD")
@EntityListeners(AuditingEntityListener.class)
public class Vinograd implements Serializable {

    private static final long serialVersionUID = 1L;

    public Vinograd() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_VINOGRAD")
    @SequenceGenerator(name="SEQ_VINOGRAD",allocationSize = 1)
    private Long id;

    private String adress;

    private String name;

    private String description;

    @CreatedDate
    private Instant date;

    @LastModifiedDate
    @Column(name = "updated_date")
    private Instant updatedDate;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_ID")
    private User user;

    @OneToMany(mappedBy="vinograd")
    private Set<VinogradVinovaloza> vinogradVinovalozas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<VinogradVinovaloza> getVinogradHasVinovalozas() {
        return vinogradVinovalozas;
    }

    public void setVinogradHasVinovalozas(Set<VinogradVinovaloza> vinogradVinovalozas) {
        this.vinogradVinovalozas = vinogradVinovalozas;
    }
}
