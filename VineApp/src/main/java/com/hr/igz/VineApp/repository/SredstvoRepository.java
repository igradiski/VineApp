package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;

@Repository
public interface SredstvoRepository extends JpaRepository<ZastitnoSredstvo, Long> {

	Boolean existsByName(String name);

    Page<ZastitnoSredstvo> findByNameContaining(String name, Pageable paging);
}
