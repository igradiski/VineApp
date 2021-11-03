package com.hr.igz.VineApp.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="USER")
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

	@OneToMany(mappedBy = "user",orphanRemoval = true)
	private Set<Vinograd> vinogradi;
}