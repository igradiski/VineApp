package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.Vinograd;
import com.hr.igz.VineApp.domain.VinogradVinovaloza;
import com.hr.igz.VineApp.domain.Vinovaloza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface VinogradHasLozaRepository extends JpaRepository<VinogradVinovaloza, Long> {

    VinogradVinovaloza findByUserAndVinogradAndVinovaloza(User user, Vinograd vinograd, Vinovaloza loza);

    Collection<VinogradVinovaloza> findByVinograd(Vinograd vinograd);

    Page<VinogradVinovaloza> findByVinograd(Vinograd vinograd, Pageable paging);
}
