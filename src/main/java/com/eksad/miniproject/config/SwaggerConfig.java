package com.eksad.miniproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

@EnableSwagger2
@Configuration
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
	@Bean
	public Docket miniprojectAPI() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.eksad.miniproject"))
				.paths(regex("/api.*"))//scaning path dengan prefix '/api'
				.build()
				.apiInfo(metaInfo()) //Assign metaInfo
				.tags(new Tag ("Cities", "Cities Manajement API"),
						new Tag ("Subdistricts", "Subdistrict Manajament API"),
						new Tag ("Get Data API", "GetById, GetByName, GetAll"),
						new Tag ("Data Manipulation API","Save, Update, Delete")
						);
	}
	
	//Pembuatan metaInfo untuk apiInf
	private ApiInfo metaInfo() {
		ApiInfo apiInfo = new ApiInfo(
				"NES Data Management REST API", // judul API
				"Rest API Collection For NES", //Deskripsi API
				"1.0.0", //Version API
				"http://www.google.com", //Term & Service URL
				new Contact("Imat Rohimat", "https://blogs.itb.ac.id/imatrohimat/", "imat.neutron@gmail.com"), //Contact Information
				"IM 2.0", //License Name
				"http://www.google.com/license", //License URL
				Collections.emptyList());
		return apiInfo;
				
		
	}

}
