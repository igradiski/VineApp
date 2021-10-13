package com.hr.igz.VineApp.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Table(name="TIP_ZASTITNOG_SREDSTVA")
@Data
public class TipZastitnogSredstva implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name="date")
	private Instant date;
	
	@OneToMany(mappedBy = "tipZastitnogSredstva")
	@JsonManagedReference
	private List<ZastitnoSredstvo> sredstva;

}
