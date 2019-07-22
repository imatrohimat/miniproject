package com.eksad.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eksad.miniproject.DAO.CityDAO;

@Repository
public interface CityRepository extends JpaRepository <CityDAO, Long> {

}
