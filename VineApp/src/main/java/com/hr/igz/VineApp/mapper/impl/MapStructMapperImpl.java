package com.hr.igz.VineApp.mapper.impl;

import java.math.BigDecimal;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.mapper.MapStructMapper;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class MapStructMapperImpl implements MapStructMapper {

	TipSredstvaRepository tipSredstvaRepository;

	@Autowired
	public MapStructMapperImpl(TipSredstvaRepository tipSredstvaRepository) {
		this.tipSredstvaRepository = tipSredstvaRepository;
	}

	@Override
	public ZastitnoSredstvo sredstvoDtoToZastitnoSredstvo(SredstvoDto sredstvo) {

		if (sredstvo == null) {
			return null;
		}
		ZastitnoSredstvo zastSredstvo = new ZastitnoSredstvo();
		try {
			zastSredstvo.setName(sredstvo.getName());
			zastSredstvo.setDescription(sredstvo.getDescription());
			zastSredstvo.setComposition(sredstvo.getComposition());
			zastSredstvo.setGroup(sredstvo.getGroup());
			zastSredstvo.setFormulation(sredstvo.getFormulation());
			zastSredstvo.setTypeOfAction(sredstvo.getTypeOfAction());
			zastSredstvo.setUsage(sredstvo.getUsage());
			zastSredstvo.setTipZastitnogSredstva(tipSredstvaRepository.getById(sredstvo.getTypeOfMedium()));
			zastSredstvo.setConcentration(BigDecimal.valueOf(Double.valueOf(sredstvo.getConcentration())));
			zastSredstvo.setDosageOn100(BigDecimal.valueOf(Double.valueOf(sredstvo.getDosageOn100())));
			zastSredstvo.setWaiting(sredstvo.getWaiting());
			zastSredstvo.setDate(Instant.now());
			zastSredstvo.setApproved(1);
		}catch (Exception e) {
			log.info(e.getMessage());
			// TODO: handle exception
		}
		return zastSredstvo;
	}

}
