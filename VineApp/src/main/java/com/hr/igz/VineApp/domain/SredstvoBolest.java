package com.hr.igz.VineApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


/**
 * The persistent class for the sredstvo_has_bolest database table.
 * 
 */
@Entity
@Table(name="sredstvo_has_bolest")
@EntityListeners(AuditingEntityListener.class)
public class SredstvoBolest implements Serializable {

	private static final long serialVersionUID = 1L;

	public SredstvoBolest() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_SREDBOLEST")
	@SequenceGenerator(name="SEQ_SREDBOLEST",allocationSize = 1)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BOLEST_id")
	@JsonBackReference
	private Bolest bolest;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ZASTITNO_SREDSTVO_id")
	@JsonBackReference
	private ZastitnoSredstvo zastitnoSredstvo;

	@CreatedDate
	private Instant date;

	@LastModifiedDate
	@Column(name = "updated_date")
	private Instant updatedDate;

	private int approved;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Bolest getBolest() {
		return bolest;
	}

	public void setBolest(Bolest bolest) {
		this.bolest = bolest;
	}

	public ZastitnoSredstvo getZastitnoSredstvo() {
		return zastitnoSredstvo;
	}

	public void setZastitnoSredstvo(ZastitnoSredstvo zastitnoSredstvo) {
		this.zastitnoSredstvo = zastitnoSredstvo;
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

	public int getApproved() {
		return approved;
	}

	public void setApproved(int approved) {
		this.approved = approved;
	}
}