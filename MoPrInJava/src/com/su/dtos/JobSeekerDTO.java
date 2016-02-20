package com.su.dtos;

/**
 * JobSeekerDTO extending PersonDTO
 * Data Transfer Object
 * @author Shreyas and Prateek
 *
 */
public class JobSeekerDTO extends PersonDTO {
	private String phoneNo;
	private String experience;
	private String skills;

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getSkills() {
		return skills;
	}

	public String getExperience() {
		return experience;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}
}
