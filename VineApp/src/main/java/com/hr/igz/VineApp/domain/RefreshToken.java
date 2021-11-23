package com.hr.igz.VineApp.domain;

import java.time.Instant;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name = "REFRESH_TOKEN")
@Data
@NoArgsConstructor
public class RefreshToken {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@Column(nullable = false, unique = true)
	private String token;

	@Column(name="expire")
	private Instant expire;

}
