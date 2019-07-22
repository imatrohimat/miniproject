
package com.eksad.miniproject.DAO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cities")
public class CityDAO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String cityName;
	
	public CityDAO() {
	}
	public CityDAO(Long id, String cityName) {
		this.id=id;
		this.cityName=cityName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	
	
}
	
	
	
