package com.hr.igz.VineApp.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

	private String name;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private TipZastitnogSredstva tipZastitnogSredstva;

	private String description;

	private String composition;

	private String group;

	private String formulation;

	@Column(name = "Type_of_action")
	private String typeOfAction;
	
	private String usage;

	private Float concentration;

	@Column(name = "Dosage_on_100")
	private Float dosageOn100;

	private Integer waiting;
	
	private Integer approved;
	
	private Instant date;

	
	
	

}
