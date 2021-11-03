package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.SredstvoHasBolest;
import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SredstvoBolestRepository extends JpaRepository<SredstvoHasBolest,Long> {

    SredstvoHasBolest findByZastitnoSredstvoAndBolest(ZastitnoSredstvo sredstvo, Bolest bolest);

    Page<SredstvoHasBolest> findByZastitnoSredstvoAndBolest(ZastitnoSredstvo sredstvo, Bolest bolest, Pageable pageable);

    Page<SredstvoHasBolest> findByBolest(Bolest bolest, Pageable paging);

    Page<SredstvoHasBolest> findByZastitnoSredstvo(ZastitnoSredstvo sredstvo, Pageable paging);
}
