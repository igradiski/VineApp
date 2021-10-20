package com.hr.igz.VineApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.igz.VineApp.domain.FenozafaRazvoja;

@Repository
public interface FenofazaRepository extends JpaRepository<FenozafaRazvoja, Long>{

	boolean existsByName(String name);

	FenozafaRazvoja findByName(String name);

}
