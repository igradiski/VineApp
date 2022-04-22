package com.hr.igz.VineApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;


@Entity
@Table(name="BOLEST")
@EntityListeners(AuditingEntityListener.class)
public class Bolest implements Serializable {

	private static final long serialVersionUID = 1L;

	public Bolest() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_BOLEST")
	@SequenceGenerator(name="SEQ_BOLEST",allocationSize = 1)
	@Column(name="id")
	private Long id;

	private String description;

	private String name;

	@OneToMany(mappedBy="bolest",orphanRemoval = true)
	private Set<BolestFaza> bolestFazas;

	@OneToMany(mappedBy="bolest",orphanRemoval = true)
	private Set<SredstvoBolest> sredstvoBolests;

	@CreatedDate
	private Instant date;

	@LastModifiedDate
	@Column(name = "updated_date")
	private Instant updatedDate;

	@JsonIgnore
	private byte[] picture;

	private String picture_name;

	private int approved;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<BolestFaza> getBolestHasFazas() {
		return bolestFazas;
	}

	public void setBolestHasFazas(Set<BolestFaza> bolestFazas) {
		this.bolestFazas = bolestFazas;
	}

	public Set<SredstvoBolest> getSredstvoHasBolests() {
		return sredstvoBolests;
	}

	public void setSredstvoHasBolests(Set<SredstvoBolest> sredstvoBolests) {
		this.sredstvoBolests = sredstvoBolests;
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

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getPicture_name() {
		return picture_name;
	}

	public void setPicture_name(String picture_name) {
		this.picture_name = picture_name;
	}

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}
}