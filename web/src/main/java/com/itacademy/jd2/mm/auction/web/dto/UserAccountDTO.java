package com.itacademy.jd2.mm.auction.web.dto;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class UserAccountDTO {

	private Integer id;

	private String role;
	private String email;
	private String password;

	private Date created;
	private Date updated;
	
	@NotNull
    @Valid
    private PersonalDataDTO personalData = new PersonalDataDTO();
	
	public PersonalDataDTO getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalDataDTO personalData) {
		this.personalData = personalData;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password =password; //PasswordUtils.generateSecurePassword(password, PasswordUtils.getSalt(30)
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
