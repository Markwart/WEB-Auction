package com.itacademy.jd2.mm.auction.web.dto;

import java.util.Date;

public class AdminCommunicationDTO {

	private Integer id;

	private Date created;

	private Date updated;
	
	private String theme, text;
	
	private Integer userFromId;
	private String userFromEmail;


	public Integer getUserFromId() {
		return userFromId;
	}

	public void setUserFromId(Integer userFromId) {
		this.userFromId = userFromId;
	}

	public String getUserFromEmail() {
		return userFromEmail;
	}

	public void setUserFromEmail(String userFromEmail) {
		this.userFromEmail = userFromEmail;
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
