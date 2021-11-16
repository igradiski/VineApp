package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.Spricanje;
import com.hr.igz.VineApp.domain.SpricanjeHasZastitnoSredstvo;
import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.DoubleStream;

@Repository
public interface SpricanjeSredstvoRepository extends JpaRepository<SpricanjeHasZastitnoSredstvo, Long> {


    Page<SpricanjeHasZastitnoSredstvo> findAllBySpricanje(Spricanje spricanje, Pageable paging);

    SpricanjeHasZastitnoSredstvo findAllBySpricanjeAndZastitnoSredstvo(Spricanje spricanje, ZastitnoSredstvo sredstvo);

}
