package com.hr.igz.VineApp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.igz.VineApp.domain.FenozafaRazvoja;

import java.util.Optional;

@Repository
public interface FenofazaRepository extends JpaRepository<FenozafaRazvoja, Long>{

	boolean existsByName(String name);


	Optional<FenozafaRazvoja> findByName(String name);

	Page<FenozafaRazvoja> findByNameContaining(String name, Pageable paging);

}
