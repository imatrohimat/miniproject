package com.eksad.miniproject.DAO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="provinces")
public class ProvinceDAO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String provinceName;
	
	public ProvinceDAO() {
	}
	public ProvinceDAO(Long id, String provinceName) {
		this.id=id;
		this.provinceName=provinceName;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
	
	
	
}
