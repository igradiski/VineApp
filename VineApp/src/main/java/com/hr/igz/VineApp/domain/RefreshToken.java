package com.hr.igz.VineApp.domain;

import javax.persistence.*;
import java.time.Instant;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "REFRESH_TOKEN")
public class RefreshToken {

	public RefreshToken() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_REFTOK")
	@SequenceGenerator(name="SEQ_REFTOK",allocationSize = 1)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(nullable = false, unique = true)
	private String token;

	@Column(name="expire")
	private Instant expire;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Instant getExpire() {
		return expire;
	}

	public void setExpire(Instant expire) {
		this.expire = expire;
	}
}
