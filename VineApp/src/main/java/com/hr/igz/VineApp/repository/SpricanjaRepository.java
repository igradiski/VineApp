package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.Spricanje;
import com.hr.igz.VineApp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpricanjaRepository extends JpaRepository<Spricanje,Long> {

    Page<Spricanje> findAllByUser(User fejkUser, Pageable paging);
}
