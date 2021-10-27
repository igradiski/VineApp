package com.hr.igz.VineApp.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the sredstvo_has_bolest database table.
 * 
 */
@Entity(name = "SredstvoHasBolest")
@Table(name="sredstvo_has_bolest")
@Data
@NoArgsConstructor
public class SredstvoHasBolest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private Instant date;

	@ManyToOne(fetch=FetchType.LAZY)
	private Bolest bolest;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ZASTITNO_SREDSTVO_id")
	private ZastitnoSredstvo zastitnoSredstvo;


}