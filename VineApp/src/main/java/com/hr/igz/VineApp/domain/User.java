package com.hr.igz.VineApp.domain;

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
@Table(name="USER_ACC")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public User() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_USER")
	@SequenceGenerator(name="SEQ_USER",allocationSize = 1)
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
	
	@OneToMany(mappedBy = "user")
	private Set<RefreshToken> refreshToken;

	@OneToMany(mappedBy="user")
	private List<UserRole> userRoles;

	@OneToMany(mappedBy = "user",orphanRemoval = true)
	private Set<Vinograd> vinogradi;

	@OneToMany(mappedBy="user")
	private Set<VinogradVinovaloza> vinogradVinovalozas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getAktivan() {
		return aktivan;
	}

	public void setAktivan(int aktivan) {
		this.aktivan = aktivan;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<RefreshToken> getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(Set<RefreshToken> refreshToken) {
		this.refreshToken = refreshToken;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<Vinograd> getVinogradi() {
		return vinogradi;
	}

	public void setVinogradi(Set<Vinograd> vinogradi) {
		this.vinogradi = vinogradi;
	}

	public Set<VinogradVinovaloza> getVinogradHasVinovalozas() {
		return vinogradVinovalozas;
	}

	public void setVinogradHasVinovalozas(Set<VinogradVinovaloza> vinogradVinovalozas) {
		this.vinogradVinovalozas = vinogradVinovalozas;
	}
}