package com.itacademy.jd2.mm.auction.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class MessageDTO {

	private Integer id;

	@NotNull
	private String theme, text;

	@NotNull
	private Integer userFromId, userWhomId;
	private String userFromEmail, userWhomEmail;
	
	@NotNull
	private Integer itemId;
	private String itemName;

	private Date created;
	private Date updated;
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getUserFromId() {
		return userFromId;
	}

	public void setUserFromId(Integer userFromId) {
		this.userFromId = userFromId;
	}

	public Integer getUserWhomId() {
		return userWhomId;
	}

	public void setUserWhomId(Integer userWhomId) {
		this.userWhomId = userWhomId;
	}

	public String getUserFromEmail() {
		return userFromEmail;
	}

	public void setUserFromEmail(String userFromEmail) {
		this.userFromEmail = userFromEmail;
	}

	public String getUserWhomEmail() {
		return userWhomEmail;
	}

	public void setUserWhomEmail(String userWhomEmail) {
		this.userWhomEmail = userWhomEmail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
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

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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
