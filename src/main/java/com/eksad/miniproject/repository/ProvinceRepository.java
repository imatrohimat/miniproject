package com.eksad.miniproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.eksad.miniproject.DAO.ProvinceDAO;

@Repository
public interface ProvinceRepository extends JpaRepository<ProvinceDAO, Long> {

}
