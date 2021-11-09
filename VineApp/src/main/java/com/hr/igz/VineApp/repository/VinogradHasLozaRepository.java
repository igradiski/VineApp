package com.hr.igz.VineApp.repository;

import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.Vinograd;
import com.hr.igz.VineApp.domain.VinogradHasVinovaloza;
import com.hr.igz.VineApp.domain.Vinovaloza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface VinogradHasLozaRepository extends JpaRepository<VinogradHasVinovaloza, Long> {

    VinogradHasVinovaloza findByUserAndVinogradAndVinovaloza(User user, Vinograd vinograd, Vinovaloza loza);

    Collection<VinogradHasVinovaloza> findByVinograd(Vinograd vinograd);

    Page<VinogradHasVinovaloza> findByVinograd(Vinograd vinograd, Pageable paging);
}
