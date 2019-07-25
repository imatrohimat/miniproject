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

import com.eksad.miniproject.DAO.ProvinceDAO;
import com.eksad.miniproject.entity.Province;
import com.eksad.miniproject.exception.DataNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(tags ="Provinces")
public class ProvinceCrud {

	@Autowired
	private  ProvinceDAO provRepository;

	@ApiOperation(value = "API to retrieve all Provinces data", 
			notes = "return All 's data with 3SON Format", 
			tags = "Get Data API")
	
	@GetMapping(value = "/provinces")
	public List<Province> getProvinceDAOs() {
		return provRepository.findAll();
	}
	
	@GetMapping(value= "/idProvince/{id}")
	public ResponseEntity<Province> GetProvById(@PathVariable(value = "id") Long provId) throws DataNotFoundException {

		 Province prov = provRepository.findById(provId)
				.orElseThrow(() -> new DataNotFoundException("City not found for this id :: " + provId));

		return ResponseEntity.ok().body(prov);
	}

//
//	@GetMapping(value= "/nameProv/{name}")
//	public ResponseEntity<Province> GetProvByName(@PathVariable(value = "name") String provName){
//		Province provReturn = provRepository.findByProvName(provName);
//		return ResponseEntity.ok().body(provReturn);
//	}
	
	
	@ApiOperation(value = "save new Province data", 
			notes = "save new Province data to database", 
		tags = "Data Manipulation API")


	@PostMapping(value = "/addProvince")
	public Province InsertProv(@Valid @RequestBody Province province) {
		return provRepository.save(province);
	}
	
	
	@ApiOperation(value = "Update new province data", 
			notes = "Update new Province data to database", 
			tags = "Data Manipulation API")


	
	@PutMapping(value= "/updateProvince/{id}")
	public ResponseEntity<Province> updateProv(@Valid @RequestBody Province Update,
			@PathVariable(value = "id") Long provId) throws DataNotFoundException {

		Province prov = provRepository.findById(provId)
				.orElseThrow(() -> new DataNotFoundException("City not found for this id :: " + provId));

		prov.setProvince_name(Update.getProvince_name());
		prov.setId(Update.getId());

		final Province updateSub = provRepository.save(prov);
		return ResponseEntity.ok().body(updateSub);
	}
	
	
	@ApiOperation(value = "Delete new Province data", 
			notes = "Delete new Province data to database", 
			tags = "Data Manipulation API")

	
	@DeleteMapping("/deleteProvince/{id}")
	public Map<String, Boolean> deleteProv(@PathVariable(value = "id") Long provId) throws DataNotFoundException {
		Province sub = provRepository.findById(provId)
				.orElseThrow(() -> new DataNotFoundException("Province not found for this id :: " + provId));

	provRepository.delete(sub);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Data dihapus", Boolean.TRUE);
		return response;
	}
	
}