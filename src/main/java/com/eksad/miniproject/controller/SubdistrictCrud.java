package com.eksad.miniproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eksad.miniproject.DAO.SubdistrictDAO;
import com.eksad.miniproject.entity.Subdistrict;
import com.eksad.miniproject.exception.DataNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/nes")
@Api(tags ="Subdistricts")
public class SubdistrictCrud {

	
	@Autowired
	private  SubdistrictDAO subRepository;

	@ApiOperation(value = "API to retrieve all subdistrics data", 
			notes = "return All 's data with 3SON Format", 
			tags = "Get Data API")
	
	@GetMapping(value = "/subdistrics")
	public List<Subdistrict> getSubdistrictDAOs() {
		return subRepository.findAll();
	}
	
	@GetMapping(value= "/idSub/{id}")
	public ResponseEntity<Subdistrict> GetSubById(@PathVariable(value = "id") Long subId) throws DataNotFoundException {

		 Subdistrict sub = subRepository.findById(subId)
				.orElseThrow(() -> new DataNotFoundException("City not found for this id :: " + subId));

		return ResponseEntity.ok().body(sub);
	}


	@GetMapping(value= "/nameSub/{name}")
	public ResponseEntity<Subdistrict> GetSubByName(@PathVariable(value = "name") String subName){
		Subdistrict subReturn = subRepository.findBySubName(subName);
		return ResponseEntity.ok().body(subReturn);
	}
	
	
	@ApiOperation(value = "save new City data", 
			notes = "save new City data to database", 
		tags = "Data Manipulation API")


	@PostMapping(value = "/addSub")
	public Subdistrict InsertSub(@Valid @RequestBody Subdistrict subdistrict) {
		return subRepository.save(subdistrict);
	}
	
	
	@ApiOperation(value = "Update new City data", 
			notes = "Update new City data to database", 
			tags = "Data Manipulation API")


	
	@PutMapping(value= "/updateSub/{id}")
	public ResponseEntity<Subdistrict> updateSub(@Valid @RequestBody Subdistrict Update,
			@PathVariable(value = "id") Long subId) throws DataNotFoundException {

		Subdistrict sub = subRepository.findById(subId)
				.orElseThrow(() -> new DataNotFoundException("City not found for this id :: " + subId));

		sub.setSubName(Update.getSubName());
		sub.setId(Update.getId());

		final Subdistrict updateSub = subRepository.save(sub);
		return ResponseEntity.ok().body(updateSub);
	}
	
	
	
	@ApiOperation(value = "Delete new City data", 
			notes = "Delete new City data to database", 
			tags = "Data Manipulation API")

	
	@DeleteMapping("/deleteSub/{id}")
	public Map<String, Boolean> deleteSub(@PathVariable(value = "id") Long subId) throws DataNotFoundException {
		Subdistrict sub = subRepository.findById(subId)
				.orElseThrow(() -> new DataNotFoundException("Province not found for this id :: " + subId));

		subRepository.delete(sub);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Data dihapus", Boolean.TRUE);
		return response;
	}
	
}

