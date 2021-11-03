package com.hr.igz.VineApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity(name="Spricanje")
@Table(name="SPRICANJE")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString(onlyExplicitlyIncluded = true)
public class Spricanje {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

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
    private Set<SpricanjeHasZastitnoSredstvo> spricanjeHasZastitnoSredstvos;
}
