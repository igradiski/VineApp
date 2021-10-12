package com.hr.igz.VineApp.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="TipZastitnogSredstva")
@Table(name="TIP_ZASTITNOG_SREDSTVA")
@Data
@NoArgsConstructor
public class TipZastitnogSredstva implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name="date")
	private Instant date;
	
	@OneToMany(
			mappedBy = "tipZastitnogSredstva",
			cascade = CascadeType.ALL)
	private Set<ZastitnoSredstvo> sredstva;

}
