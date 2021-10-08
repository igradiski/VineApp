package com.hr.igz.VineApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;

@Repository
public interface TipSredstvaRepository extends JpaRepository<TipZastitnogSredstva, Long>{

	boolean existsByName(String name);

}
