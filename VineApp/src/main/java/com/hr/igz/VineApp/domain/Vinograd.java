package com.hr.igz.VineApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JoinFormula;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;


@Entity(name="Vinograd")
@Table(name="VINOGRAD")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString(onlyExplicitlyIncluded = true)
public class Vinograd implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    private Set<VinogradHasVinovaloza> vinogradHasVinovalozas;

}
