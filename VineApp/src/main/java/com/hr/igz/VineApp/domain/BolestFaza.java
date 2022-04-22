package com.hr.igz.VineApp.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;



/**
 * The persistent class for the bolest_has_faza database table.
 * 
 */
@Entity
@Table(name="bolest_has_faza")
@EntityListeners(AuditingEntityListener.class)
public class BolestFaza implements Serializable {

	private static final long serialVersionUID = 1L;

	public BolestFaza() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_BOLESTFAZA")
	@SequenceGenerator(name="SEQ_BOLESTFAZA",allocationSize = 1)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BOLEST_id")
	private Bolest bolest;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FENOFAZA_RAZVOJA_id")
	private FenofazaRazvoja fenofazaRazvoja;

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

	public FenofazaRazvoja getFenofazaRazvoja() {
		return fenofazaRazvoja;
	}

	public void setFenofazaRazvoja(FenofazaRazvoja fenofazaRazvoja) {
		this.fenofazaRazvoja = fenofazaRazvoja;
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