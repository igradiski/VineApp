package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.Vinograd;
import com.hr.igz.VineApp.domain.dto.VinogradDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.DoubleStream;

public interface VinogradRepository extends JpaRepository<Vinograd,Long> {

    boolean existsByName(String name);

    Page<Vinograd> findAllByUser(User fejkUser, Pageable paging);
}
