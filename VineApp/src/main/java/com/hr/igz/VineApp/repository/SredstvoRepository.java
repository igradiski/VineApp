package com.hr.igz.VineApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;

@Repository
public interface SredstvoRepository extends JpaRepository<ZastitnoSredstvo, Long> {

	Boolean existsByName(String name);

}
