package com.hr.igz.VineApp.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity(name="spricanje_has_sredstvo")
@Table(name="spricanje_has_zastitno_sredstvo")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString(onlyExplicitlyIncluded = true)
public class SpricanjeHasZastitnoSredstvo {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    private Double dosage;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="zastitno_sredstvo_id")
    private ZastitnoSredstvo zastitnoSredstvo;

    @ManyToOne(fetch=FetchType.LAZY)
    private Spricanje spricanje;
}
