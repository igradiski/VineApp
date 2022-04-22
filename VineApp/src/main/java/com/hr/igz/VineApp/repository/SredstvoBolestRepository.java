package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.SredstvoBolest;
import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SredstvoBolestRepository extends JpaRepository<SredstvoBolest,Long> {

    SredstvoBolest findByZastitnoSredstvoAndBolest(ZastitnoSredstvo sredstvo, Bolest bolest);

    Page<SredstvoBolest> findByZastitnoSredstvoAndBolest(ZastitnoSredstvo sredstvo, Bolest bolest, Pageable pageable);

    Page<SredstvoBolest> findByBolest(Bolest bolest, Pageable paging);

    Page<SredstvoBolest> findByZastitnoSredstvo(ZastitnoSredstvo sredstvo, Pageable paging);
}
