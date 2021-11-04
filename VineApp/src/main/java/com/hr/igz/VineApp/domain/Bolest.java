package com.hr.igz.VineApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.time.Instant;
import java.util.Set;


@Entity(name="Bolest")
@Table(name="BOLEST")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@ToString(onlyExplicitlyIncluded = true)
public class Bolest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String description;

	private String name;

	@OneToMany(mappedBy="bolest",orphanRemoval = true)
	private Set<BolestHasFaza> bolestHasFazas;

	@OneToMany(mappedBy="bolest",orphanRemoval = true)
	private Set<SredstvoHasBolest> sredstvoHasBolests;

	@CreatedDate
	private Instant date;

	@LastModifiedDate
	@Column(name = "updated_date")
	private Instant updatedDate;

	@JsonIgnore
	private byte[] picture;

	private String picture_name;

	private int approved;

}