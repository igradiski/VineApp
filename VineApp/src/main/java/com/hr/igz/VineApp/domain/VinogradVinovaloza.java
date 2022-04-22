package com.hr.igz.VineApp.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name="vinograd_has_vinovaloza")
@EntityListeners(AuditingEntityListener.class)
public class VinogradVinovaloza {

    private static final long serialVersionUID = 1L;

    public VinogradVinovaloza() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_VINOGRADLOZA")
    @SequenceGenerator(name="SEQ_VINOGRADLOZA",allocationSize = 1)
    private Long id;

    private Integer quantity;

    @ManyToOne(fetch=FetchType.LAZY)
    private Vinograd vinograd;

    @ManyToOne(fetch=FetchType.LAZY)
    private Vinovaloza vinovaloza;

    @ManyToOne(fetch=FetchType.LAZY)
    private User user;

    @CreatedDate
    private Instant date;

    @LastModifiedDate
    @Column(name = "updated_date")
    private Instant updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Vinograd getVinograd() {
        return vinograd;
    }

    public void setVinograd(Vinograd vinograd) {
        this.vinograd = vinograd;
    }

    public Vinovaloza getVinovaloza() {
        return vinovaloza;
    }

    public void setVinovaloza(Vinovaloza vinovaloza) {
        this.vinovaloza = vinovaloza;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
}
