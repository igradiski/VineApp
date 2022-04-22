package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.BolestFaza;
import com.hr.igz.VineApp.domain.FenofazaRazvoja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FazaBolestRepository extends JpaRepository<BolestFaza,Long> {

    BolestFaza findByBolestAndFenofazaRazvoja(Bolest bolest, FenofazaRazvoja faza);

    Page<BolestFaza> findByBolestAndFenofazaRazvoja(Bolest bolest, FenofazaRazvoja faza, Pageable paging);

    Page<BolestFaza> findByFenofazaRazvoja(FenofazaRazvoja faza, Pageable paging);

    Page<BolestFaza> findByBolest(Bolest bolest, Pageable paging);
}
