package com.hr.igz.VineApp.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


@Entity(name="FenofazaRazvoja")
@Table(name="fenozafa_razvoja")
@Data
@NoArgsConstructor
public class FenozafaRazvoja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Instant date;

	private String name;

	@Column(name="time_of_usage")
	private String timeOfUsage;

	@OneToMany(mappedBy="fenozafaRazvoja")
	private Set<BolestHasFaza> bolestHasFazas;

}