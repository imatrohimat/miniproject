package com.eksad.miniproject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

import lombok.ToString;


@Data
@ToString(callSuper = true)
@Entity
@Table(name = "Provinces")
public class Province {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrement
	private long id;
	@Column(nullable = false) // not null
	
	private String province_name;
	
}
