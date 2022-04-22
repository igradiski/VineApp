package com.hr.igz.VineApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name = "ZASTITNO_SREDSTVO")
@EntityListeners(AuditingEntityListener.class)
public class ZastitnoSredstvo implements Serializable {

	private static final long serialVersionUID = 1L;

	public ZastitnoSredstvo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ZASTSREDSTVO")
	@SequenceGenerator(name="SEQ_ZASTSREDSTVO",allocationSize = 1)
	private Long id;

	@Column(name = "Name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TIP_ZASTITNOG_SREDSTVA_id")
	private TipZastitnogSredstva tipZastitnogSredstva;

	@OneToMany(mappedBy="zastitnoSredstvo",orphanRemoval = true)
	private Set<SredstvoBolest> sredstvoBolests;

	@OneToMany(mappedBy="zastitnoSredstvo",orphanRemoval = true)
	private Set<SpricanjeZastitnoSredstvo> spricanjeZastitnoSredstvos;

	private String description;

	private String composition;

	@Column(name = "medium_group")
	private String group;

	private String formulation;

	@Column(name = "Type_of_action")
	private String typeOfAction;
	
	@Column(name = "medium_usage")
	private String usage;

	private BigDecimal concentration;

	@Column(name = "Dosage_on_100")
	private BigDecimal dosageOn100;

	private Integer waiting;
	
	private Integer approved;

	@CreatedDate
	private Instant date;

	@LastModifiedDate
	@Column(name = "updated_date")
	private Instant updatedDate;

	@JsonIgnore
	private byte[] picture;

	private String picture_name;

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

	public TipZastitnogSredstva getTipZastitnogSredstva() {
		return tipZastitnogSredstva;
	}

	public void setTipZastitnogSredstva(TipZastitnogSredstva tipZastitnogSredstva) {
		this.tipZastitnogSredstva = tipZastitnogSredstva;
	}

	public Set<SredstvoBolest> getSredstvoHasBolests() {
		return sredstvoBolests;
	}

	public void setSredstvoHasBolests(Set<SredstvoBolest> sredstvoBolests) {
		this.sredstvoBolests = sredstvoBolests;
	}

	public Set<SpricanjeZastitnoSredstvo> getSpricanjeHasZastitnoSredstvos() {
		return spricanjeZastitnoSredstvos;
	}

	public void setSpricanjeHasZastitnoSredstvos(Set<SpricanjeZastitnoSredstvo> spricanjeZastitnoSredstvos) {
		this.spricanjeZastitnoSredstvos = spricanjeZastitnoSredstvos;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getFormulation() {
		return formulation;
	}

	public void setFormulation(String formulation) {
		this.formulation = formulation;
	}

	public String getTypeOfAction() {
		return typeOfAction;
	}

	public void setTypeOfAction(String typeOfAction) {
		this.typeOfAction = typeOfAction;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public BigDecimal getConcentration() {
		return concentration;
	}

	public void setConcentration(BigDecimal concentration) {
		this.concentration = concentration;
	}

	public BigDecimal getDosageOn100() {
		return dosageOn100;
	}

	public void setDosageOn100(BigDecimal dosageOn100) {
		this.dosageOn100 = dosageOn100;
	}

	public Integer getWaiting() {
		return waiting;
	}

	public void setWaiting(Integer waiting) {
		this.waiting = waiting;
	}

	public Integer getApproved() {
		return approved;
	}

	public void setApproved(Integer approved) {
		this.approved = approved;
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
}
