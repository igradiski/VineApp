package com.hr.igz.VineApp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.igz.VineApp.domain.Bolest;

@Repository
public interface BolestRepository extends JpaRepository<Bolest, Long>{

	boolean existsByName(String name);

	Optional<Bolest> findByName(String name);

	Page<Bolest> findByNameContaining(String name, Pageable paging);

}
