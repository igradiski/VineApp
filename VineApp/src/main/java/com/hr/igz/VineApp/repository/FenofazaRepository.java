package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.FenofazaRazvoja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FenofazaRepository extends JpaRepository<FenofazaRazvoja, Long>{

	boolean existsByName(String name);

	Optional<FenofazaRazvoja> findByName(String name);

	Page<FenofazaRazvoja> findByNameContainingIgnoreCase(String name,Pageable paging);

}
