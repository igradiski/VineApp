package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;

public interface MapStructMapper {

	public ZastitnoSredstvo sredstvoDtoToZastitnoSredstvo(SredstvoDto sredstvo);

}
