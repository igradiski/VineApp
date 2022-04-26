package com.hr.igz.VineApp.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name="SPRICANJE")
@EntityListeners(AuditingEntityListener.class)
public class Spricanje {

    private static final long serialVersionUID = 1L;

    public Spricanje() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_SPRICANJE")
    @SequenceGenerator(name="SEQ_SPRICANJE",allocationSize = 1)
    private Long id;

    @CreatedDate
    private Instant date;

    @LastModifiedDate
    @Column(name = "updated_date")
    private Instant updatedDate;

    private String description;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_ID")
    private User user;

    private int water;

    @OneToMany(mappedBy="spricanje")
    private Set<SpricanjeZastitnoSredstvo> spricanjeZastitnoSredstvos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public Set<SpricanjeZastitnoSredstvo> getSpricanjeHasZastitnoSredstvos() {
        return spricanjeZastitnoSredstvos;
    }

    public void setSpricanjeHasZastitnoSredstvos(Set<SpricanjeZastitnoSredstvo> spricanjeZastitnoSredstvos) {
        this.spricanjeZastitnoSredstvos = spricanjeZastitnoSredstvos;
    }
}
