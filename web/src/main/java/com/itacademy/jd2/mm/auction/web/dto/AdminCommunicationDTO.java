package com.itacademy.jd2.mm.auction.web.dto;

import java.util.Date;

public class AdminCommunicationDTO {

	private Integer id;

	private Date created;

	private Date updated;
	
	private String theme, text;
	
	private Integer userAccountId;
	private String userAccountEmail;

	public String getUserAccountEmail() {
		return userAccountEmail;
	}

	public void setUserAccountEmail(String userAccountEmail) {
		this.userAccountEmail = userAccountEmail;
	}

	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
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

}
