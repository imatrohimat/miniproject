package com.eksad.miniproject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
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
import com.eksad.miniproject.exception.DataNotFoundException;
import com.eksad.miniproject.model.Province;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/nes")
@Api(tags = "Provinces")
public class ProvinceController {

	@Autowired
	private ProvinceDAO provinceRepository;

	@ApiOperation(value = "API to retrieve all Province's data", 
			notes = "return All 's data with 3SON Format", 
			tags = "Get Data API")

	@GetMapping("/provinces")
	public List<Province> getAllProvinceDAOs() {
		return provinceRepository.findAll();
	}

	@ApiOperation(value = "API to retrieve all Province's data by id", 
			notes = "return All province's data with 3SON Format", 
			tags = "Get Data API")

	@GetMapping("province/{id}")
	public ResponseEntity<Province> GetProvinceById(@PathVariable(value = "id") Long provinceId)
			throws DataNotFoundException {

		Province province = provinceRepository.findById(provinceId)
				.orElseThrow(() -> new DataNotFoundException("Province not found for this id :  " + provinceId));

		return ResponseEntity.ok().body(province);
	}

	@ApiOperation(value = "API to retrieve all Province's data by id", 
			notes = "return All province's data with 3SON Format", 
			tags = "Get Data API")

	@GetMapping("/province/{name}/{id}")
	public ResponseEntity<List<Province>> GetProvinceByName(
			@PathVariable(value = "Province Name") String provinceName, @PathVariable(value = "id") Long id) {

		Province province = new Province();

		province.setProvinceName(provinceName);
		province.setId(id);

		Example<Province> provinceExample = Example.of(province);

		List<Province> provinceReturn = provinceRepository.findAll(provinceExample);

		return ResponseEntity.ok().body(provinceReturn);
	}

	@ApiOperation(value = "save new Province data", 
		notes = "save new Province data to database", 
			tags = "Data Manipulation API")

@PostMapping("/province")
	public Province InsertProvince(@Valid @RequestBody Province province) {
		return provinceRepository.save(province);
	}

	@ApiOperation(value = "Update new Province data", notes = "Update new Province data to database", tags = "Data Manipulation API")

	@PutMapping("/province/{id}")
	public ResponseEntity<Province> UpdateProvince(@Valid @RequestBody Province provinceRequest,
			@PathVariable(value = "id") Long provinceId) throws DataNotFoundException {

		Province province = provinceRepository.findById(provinceId)
				.orElseThrow(() -> new DataNotFoundException("Province not found for this id : " + provinceId));

		province.setProvinceName(provinceRequest.getProvinceName());
		province.setId(provinceRequest.getId());

		final Province updateProvince = provinceRepository.save(province);

		return ResponseEntity.ok().body(updateProvince);
	}

	@ApiOperation(value = "Delete new Province data", notes = "Delete new Province data to database", tags = "Data Manipulation API")

	@DeleteMapping("/province/{id}")
	public Map<String, Boolean> deleteProvince(@PathVariable(value = "id") Long provinceId)
			throws DataNotFoundException {
		Province province = provinceRepository.findById(provinceId)
				.orElseThrow(() -> new DataNotFoundException("Province not found for this id :: " + provinceId));

		provinceRepository.delete(province);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
