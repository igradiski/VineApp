package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.BolestHasFaza;
import com.hr.igz.VineApp.domain.FenozafaRazvoja;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FazaBolestRepository extends JpaRepository<BolestHasFaza,Long> {

    BolestHasFaza findByBolestAndFenozafaRazvoja(Bolest bolest, FenozafaRazvoja faza);

    Page<BolestHasFaza> findByBolestAndFenozafaRazvoja(Bolest bolest, FenozafaRazvoja faza, Pageable paging);

    Page<BolestHasFaza> findByFenozafaRazvoja(FenozafaRazvoja faza, Pageable paging);

    Page<BolestHasFaza> findByBolest(Bolest bolest, Pageable paging);
}
