package com.eksad.miniproject.DAO;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eksad.miniproject.model.City;

@Repository
public interface CityDAO extends JpaRepository <City, Long> {
	
	//@Query("SELECT c FROM City c WHERE c.cityName =:name")
	public City findByCityName(String name);

}
