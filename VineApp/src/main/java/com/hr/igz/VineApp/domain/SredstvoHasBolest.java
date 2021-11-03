package com.hr.igz.VineApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Entity(name = "SredstvoHasBolest")
@Table(name="sredstvo_has_bolest")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class SredstvoHasBolest implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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


}