package com.hr.igz.VineApp.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ZASTITNO_SREDSTVO")
@Data
@NoArgsConstructor
public class ZastitnoSredstvo implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "Name")
	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="TIP_ZASTITNOG_SREDSTVA_id")
	private TipZastitnogSredstva tipZastitnogSredstva;

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
	
	private Instant date;

}
