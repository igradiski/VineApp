package com.hr.igz.VineApp.domain;

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
 * The persistent class for the bolest_has_faza database table.
 * 
 */
@Entity(name="BolestHasFaza")
@Table(name="bolest_has_faza")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BolestHasFaza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="BOLEST_id")
	private Bolest bolest;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="FENOZAFA_RAZVOJA_id")
	private FenozafaRazvoja fenozafaRazvoja;

	@CreatedDate
	private Instant date;

	@LastModifiedDate
	@Column(name = "updated_date")
	private Instant updatedDate;

	private int approved;

}