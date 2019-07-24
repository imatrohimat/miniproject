package com.eksad.miniproject.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eksad.miniproject.entity.Subdistrict;

@Repository
public interface SubdistrictDAO extends JpaRepository <Subdistrict, Long>{
	public Subdistrict findBySubName(String name);
}