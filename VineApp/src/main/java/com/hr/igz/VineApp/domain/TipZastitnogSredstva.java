package com.hr.igz.VineApp.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Table(name="TIP_ZASTITNOG_SREDSTVA")
@EntityListeners(AuditingEntityListener.class)
public class TipZastitnogSredstva implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public TipZastitnogSredstva() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_TIPSREDSTVA")
	@SequenceGenerator(name="SEQ_TIPSREDSTVA",allocationSize = 1)
	private Long id;
	
	private String name;

	@OneToMany(mappedBy = "tipZastitnogSredstva")
	private Set<ZastitnoSredstvo> sredstva;

	@CreatedDate
	private Instant date;

	@LastModifiedDate
	@Column(name = "updated_date")
	private Instant updatedDate;

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

	public Set<ZastitnoSredstvo> getSredstva() {
		return sredstva;
	}

	public void setSredstva(Set<ZastitnoSredstvo> sredstva) {
		this.sredstva = sredstva;
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
}
