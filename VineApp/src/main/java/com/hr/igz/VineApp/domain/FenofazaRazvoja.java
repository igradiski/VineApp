package com.hr.igz.VineApp.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;


@Entity
@Table(name="fenofaza_razvoja")
@EntityListeners(AuditingEntityListener.class)
public class FenofazaRazvoja implements Serializable {

	private static final long serialVersionUID = 1L;

	public FenofazaRazvoja() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_FENOFAZARAZVOJA")
	@SequenceGenerator(name="SEQ_FENOFAZARAZVOJA",allocationSize = 1)
	private Long id;

	private String name;

	private String message;

	@Column(name="time_of_usage")
	private String timeOfUsage;

	@OneToMany(mappedBy="fenofazaRazvoja",orphanRemoval = true)
	private Set<BolestFaza> bolestFazas;

	@CreatedDate
	private Instant date;

	@LastModifiedDate
	@Column(name = "updated_date")
	private Instant updatedDate;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimeOfUsage() {
		return timeOfUsage;
	}

	public void setTimeOfUsage(String timeOfUsage) {
		this.timeOfUsage = timeOfUsage;
	}

	public Set<BolestFaza> getBolestFazas() {
		return bolestFazas;
	}

	public void setBolestFazas(Set<BolestFaza> bolestFazas) {
		this.bolestFazas = bolestFazas;
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