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

import com.eksad.miniproject.DAO.CityDAO;
import com.eksad.miniproject.entity.City;
import com.eksad.miniproject.exception.DataNotFoundException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
@Api(tags ="Cities")
public class CityCrud {
	
	@Autowired
	private CityDAO cityRepository;
	

	@ApiOperation(value = "API to retrieve cities data", 
			notes = "return All 's data with 3SON Format", 
			tags = "Get Data API")

	@GetMapping(value = "/cities")
	public List<City> getAllCityDAOs() {
		return cityRepository.findAll();
	}


	@GetMapping(value= "/idCity/{id}")
	public ResponseEntity<City> GetCityById(@PathVariable(value = "id") Long cityId) throws DataNotFoundException {

		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new DataNotFoundException("City not found for this id :: " + cityId));
		return ResponseEntity.ok().body(city);
	}


	@GetMapping(value= "/nameCity/{name}")
	public ResponseEntity<City> GetCityByName(@PathVariable(value = "name") String cityName){
		City cityReturn = cityRepository.findByCityName(cityName);
		return ResponseEntity.ok().body(cityReturn);
	}

	@ApiOperation(value = "save new City data", 
			notes = "save new City data to database", 
		tags = "Data Manipulation API")

	@PostMapping(value = "/addCity")
	public City InsertCity(@Valid @RequestBody City city) {
		return cityRepository.save(city);
	}
	
	@ApiOperation(value = "Update new City data", 
			notes = "Update new City data to database", 
			tags = "Data Manipulation API")

	@PutMapping(value= "/updateCity/{id}")
	public ResponseEntity<City> updateCity(@Valid @RequestBody City Update,
			@PathVariable(value = "id") Long cityId) throws DataNotFoundException {

		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new DataNotFoundException("City not found for this id :: " + cityId));

		city.setCityName(Update.getCityName());
		city.setId(Update.getId());

		final City updateCity = cityRepository.save(city);
		return ResponseEntity.ok().body(updateCity);
	}

	
	@ApiOperation(value = "Delete new City data", 
			notes = "Delete new City data to database", 
			tags = "Data Manipulation API")

	@DeleteMapping("/deleteCity/{id}")
	public Map<String, Boolean> deleteCity(@PathVariable(value = "id") Long cityId) throws DataNotFoundException {
		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new DataNotFoundException("Province not found for this id :: " + cityId));

		cityRepository.delete(city);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Data dihapus", Boolean.TRUE);
		return response;
	}
	
	
}
