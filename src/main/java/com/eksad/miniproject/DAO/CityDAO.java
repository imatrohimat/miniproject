package com.eksad.miniproject.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eksad.miniproject.entity.City;

@Repository
public interface CityDAO extends JpaRepository <City, Long> {
	public City findByCityName(String name);

}
