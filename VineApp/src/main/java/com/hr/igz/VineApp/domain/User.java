package com.hr.igz.VineApp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="USER")
@Data
@NoArgsConstructor
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private int aktivan;

	private String email;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_LOGIN")
	private Date lastLogin;

	private String name;

	private String password;

	private String surname;

	private String username;
	
	@OneToOne(mappedBy = "user")
	private RefreshToken refreshToken;

	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;
}