package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.Vinovaloza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VinovaLozaRepository extends JpaRepository<Vinovaloza,Long> {

    boolean existsByName(String name);
}
