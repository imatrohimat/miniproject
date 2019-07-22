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

import com.eksad.miniproject.DAO.CityDAO;
import com.eksad.miniproject.exception.DataNotFoundException;
import com.eksad.miniproject.repository.CityRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/nes")
@Api(tags = "cities")
public class CityController {

	@Autowired
	private CityRepository cityRepository;

	@ApiOperation(value = "API to retrieve all City's data", 
			notes = "return All 's data with 3SON Format", 
			tags = "Get Data API")

	@GetMapping("/cities")
	public List<CityDAO> getAllCityDAOs() {
		return cityRepository.findAll();
	}

	@ApiOperation(value = "API to retrieve all City's data by id", 
			notes = "return All City's data with 3SON Format", 
			tags = "Get Data API")

	@GetMapping("/city/{id}")
	public ResponseEntity<CityDAO> GetCityById(@PathVariable(value = "id") Long cityId) throws DataNotFoundException {

		CityDAO city = cityRepository.findById(cityId)
				.orElseThrow(() -> new DataNotFoundException("City not found for this id :: " + cityId));

		return ResponseEntity.ok().body(city);
	}

	@ApiOperation(value = "API to retrieve all City's data by id", 
			notes = "return All city's data with 3SON Format", 
			tags = "Get Data API")

	@GetMapping("/city/{name}/{id}")
	public ResponseEntity<List<CityDAO>> GetCityByName(@PathVariable(value = "City Name") String cityName,
			@PathVariable(value = "id") String id) {

		CityDAO city = new CityDAO();

		city.setCityName(cityName);
		city.setCityName(id);

		Example<CityDAO> cityExample = Example.of(city);

		List<CityDAO> cityReturn = cityRepository.findAll(cityExample);

		return ResponseEntity.ok().body(cityReturn);
	}

	@ApiOperation(value = "save new City data", 
			notes = "save new City data to database", 
			tags = "Data Manipulation API")

	@PostMapping("/City")
	public CityDAO InsertCity(@Valid @RequestBody CityDAO city) {
		return cityRepository.save(city);
	}

	@ApiOperation(value = "Update new City data", notes = "Update new City data to database", tags = "Data Manipulation API")

	@PutMapping("/City/{id}")
	public ResponseEntity<CityDAO> UpdateCity(@Valid @RequestBody CityDAO cityRequest,
			@PathVariable(value = "id") Long cityId) throws DataNotFoundException {

		CityDAO city = cityRepository.findById(cityId)
				.orElseThrow(() -> new DataNotFoundException("City not found for this id :: " + cityId));

		city.setCityName(cityRequest.getCityName());
		city.setId(cityRequest.getId());

		final CityDAO updateCity = cityRepository.save(city);

		return ResponseEntity.ok().body(updateCity);
	}

	@ApiOperation(value = "Delete new City data", notes = "Delete new City data to database", tags = "Data Manipulation API")

	@DeleteMapping("/city/{id}")
	public Map<String, Boolean> deleteCity(@PathVariable(value = "id") Long cityId) throws DataNotFoundException {
		CityDAO city = cityRepository.findById(cityId)
				.orElseThrow(() -> new DataNotFoundException("Province not found for this id :: " + cityId));

		cityRepository.delete(city);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
