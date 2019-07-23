package com.eksad.miniproject.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eksad.miniproject.model.Province;

@Repository
public interface ProvinceDAO extends JpaRepository<Province, Long> {

}
