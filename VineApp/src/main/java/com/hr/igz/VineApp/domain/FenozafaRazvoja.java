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
import java.util.Set;


@Entity(name="FenofazaRazvoja")
@Table(name="fenozafa_razvoja")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class FenozafaRazvoja implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name="time_of_usage")
	private String timeOfUsage;

	@OneToMany(mappedBy="fenozafaRazvoja",orphanRemoval = true)
	private Set<BolestHasFaza> bolestHasFazas;

	@CreatedDate
	private Instant date;

	@LastModifiedDate
	@Column(name = "updated_date")
	private Instant updatedDate;

}