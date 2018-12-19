package com.itacademy.jd2.mm.auction.web.dto;

import java.util.Date;

public class MessageDTO {

	private Integer id;

	private String theme, text;

	private Integer userAccountFromId, userAccountWhomId;
	private String userAccountFromEmail, userAccountWhomEmail;

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

	public String getUserAccountFromEmail() {
		return userAccountFromEmail;
	}

	public void setUserAccountFromEmail(String userAccountFromEmail) {
		this.userAccountFromEmail = userAccountFromEmail;
	}

	public String getUserAccountWhomEmail() {
		return userAccountWhomEmail;
	}

	public void setUserAccountWhomEmail(String userAccountWhomEmail) {
		this.userAccountWhomEmail = userAccountWhomEmail;
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

	public Integer getUserAccountFromId() {
		return userAccountFromId;
	}

	public void setUserAccountFromId(Integer userAccountFromId) {
		this.userAccountFromId = userAccountFromId;
	}

	public Integer getUserAccountWhomId() {
		return userAccountWhomId;
	}

	public void setUserAccountWhomId(Integer userAccountWhomId) {
		this.userAccountWhomId = userAccountWhomId;
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
