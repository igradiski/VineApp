package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.Spricanje;
import com.hr.igz.VineApp.domain.SpricanjeHasZastitnoSredstvo;
import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpricanjeSredstvoRepository extends JpaRepository<SpricanjeHasZastitnoSredstvo, Long> {

    SpricanjeHasZastitnoSredstvo findAllBySpricanjeAndZastitnoSredstvo(Spricanje spricanje, ZastitnoSredstvo sredstvo);

    Page<SpricanjeHasZastitnoSredstvo> findBySpricanje(Spricanje spricanje, Pageable paging);
}
