package com.su.dtos;

/**
 * JobPosterDTO extending PersonDTO
 * Data Transfer Object
 * @author Shreyas and Prateek
 *
 */
public class JobPosterDTO extends PersonDTO {

	private String companyName;

	public String getCompanyName(){
		return companyName;
	}
	
	public void setCompanyName(String companyName){
		this.companyName = companyName;
	}
}
