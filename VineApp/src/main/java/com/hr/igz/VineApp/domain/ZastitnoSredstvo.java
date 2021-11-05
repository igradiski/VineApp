package com.hr.igz.VineApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;

@Entity(name="ZastitnoSredstvo")
@Table(name = "ZASTITNO_SREDSTVO")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

	@OneToMany(mappedBy="zastitnoSredstvo",orphanRemoval = true)
	private Set<SredstvoHasBolest> sredstvoHasBolests;

	@OneToMany(mappedBy="zastitnoSredstvo",orphanRemoval = true)
	private Set<SpricanjeHasZastitnoSredstvo> spricanjeHasZastitnoSredstvos;

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

}
