package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.Spricanje;
import com.hr.igz.VineApp.domain.SpricanjeZastitnoSredstvo;
import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpricanjeSredstvoRepository extends JpaRepository<SpricanjeZastitnoSredstvo, Long> {

    SpricanjeZastitnoSredstvo findAllBySpricanjeAndZastitnoSredstvo(Spricanje spricanje, ZastitnoSredstvo sredstvo);

    Page<SpricanjeZastitnoSredstvo> findBySpricanje(Spricanje spricanje, Pageable paging);
}
