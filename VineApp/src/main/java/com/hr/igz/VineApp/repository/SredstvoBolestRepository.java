package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.SredstvoHasBolest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SredstvoBolestRepository extends JpaRepository<SredstvoHasBolest,Long> {

}
