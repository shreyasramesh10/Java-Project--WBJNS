package com.su.dtos;

/**
 * PersonDTO Super class
 * Data Transfer Object
 * @author Shreyas and Prateek
 *
 */
public class PersonDTO {
	
	private String firstName;
	private String lastName;
	private String emailId;
	
	public String getFirstName(){
		return firstName;
	}
	public String getLastName(){
		return lastName;
	}
	public String getEmailId(){
		return emailId;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public void setEmailId(String emailId){
		this.emailId = emailId;
	}

}
