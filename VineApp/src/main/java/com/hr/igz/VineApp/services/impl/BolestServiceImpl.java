package com.hr.igz.VineApp.services.impl;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.dto.BolestDto;
import com.hr.igz.VineApp.exception.DeleteFailureException;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.BolestMapper;
import com.hr.igz.VineApp.repository.BolestRepository;
import com.hr.igz.VineApp.services.BolestService;
import com.hr.igz.VineApp.utils.SortingHelperUtil;

@Service
public class BolestServiceImpl implements BolestService {
	
	private BolestRepository repos;
	 
	private final BolestMapper mapper;
	
	private SortingHelperUtil sortHelper;
	
	@Autowired
	public BolestServiceImpl(BolestRepository repos,BolestMapper mapper) {
		this.mapper=mapper;
		this.repos=repos;
	}
	
	@Autowired
	public void setSortHelper(SortingHelperUtil sortHelper) {
		this.sortHelper = sortHelper;
	}

	@Override
	public ResponseEntity<Object> addBolest(BolestDto bolestDto) {

		if (repos.existsByName(bolestDto.getName())) {
			throw new ObjectAlreadyExists("Bolest toga imena vec postoji!");
		}
		Bolest bolest = mapper.BolestDtoToBolest(bolestDto);
		bolest.setDate(Instant.now());
		try {
			repos.save(bolest);
		} catch (Exception e) {
			throw new PostFailureException("Nije moguce unijeti zeljenu bolest!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Tip sredstva je uspješno kreiran");
	}

	@Override
	public ResponseEntity<Map<String, Object>> getBolestiPaged(int pageSize, int pageNo, String[] sort) {
		List<Order> orders = sortHelper.getOrdersFromArray(sort);
		Map<String, Object> response = new HashMap<>();
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(orders));
		Page<Bolest> page = repos.findAll(paging);
		response.put("bolesti",mapAllBolesti(page.getContent()));
		response.put("totalPages", page.getTotalPages());
		response.put("totalItems", page.getTotalElements());
		response.put("currentPage", page.getNumber());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	private Set<BolestDto> mapAllBolesti(List<Bolest> list){
		Set<BolestDto> set = new HashSet<BolestDto>();
		list.stream().forEach(bolest ->{
			set.add(mapper.BolestToBolestDto(bolest));
		});
		return set;
	}

	@Override
	public ResponseEntity<Object> deleteBolestByName(String name) {
		Bolest bolest = repos.findByName(name).orElseThrow(()-> new DeleteFailureException("Ne postoji objekt za brisanje!"));
		try {
			repos.delete(bolest);
		}catch (Exception e) {
			throw new DeleteFailureException(e.getMessage());
		}
		return null;
	}

}
