package com.hr.igz.VineApp.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="ROLE")
@Getter
@Setter
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@Column(name="ROLE_NAME")
	private String roleName;
	
	
	@OneToMany(mappedBy="role")
	private List<UserRole> userRoles;

}