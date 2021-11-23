package com.hr.igz.VineApp.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import com.hr.igz.VineApp.enums.ERole;
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


	@OneToMany(mappedBy="role")
	private List<UserRole> userRoles;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

}