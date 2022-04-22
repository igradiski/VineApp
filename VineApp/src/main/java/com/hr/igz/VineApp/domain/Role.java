package com.hr.igz.VineApp.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="ROLE")
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	public Role() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_ROLE")
	@SequenceGenerator(name="SEQ_ROLE",allocationSize = 1)
	private Long id;

	@OneToMany(mappedBy="role")
	private List<UserRole> userRoles;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}
}