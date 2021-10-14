package com.hr.igz.VineApp.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name="Bolest")
@Table(name="BOLEST")
@Data
@NoArgsConstructor
public class Bolest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private Instant date;

	private String description;

	private String name;

	@OneToMany(mappedBy="bolest")
	private Set<BolestHasFaza> bolestHasFazas;

	@OneToMany(mappedBy="bolest")
	private Set<SredstvoHasBolest> sredstvoHasBolests;


}