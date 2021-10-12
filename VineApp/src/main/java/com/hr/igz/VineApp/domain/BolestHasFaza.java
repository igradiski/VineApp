package com.hr.igz.VineApp.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the bolest_has_faza database table.
 * 
 */
@Entity(name="BolestHasFaza")
@Table(name="bolest_has_faza")
@Data
@NoArgsConstructor
public class BolestHasFaza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@ManyToOne(fetch=FetchType.LAZY)
	private Bolest bolest;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FENOZAFA_RAZVOJA_id")
	private FenozafaRazvoja fenozafaRazvoja;

}