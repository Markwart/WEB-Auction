package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

public class AdminCommunication extends BaseEntity implements IAdminCommunication {

	private String theme, text;
	private IUserAccount userFrom;

	@Override
	public String getTheme() {
		return theme;
	}

	@Override
	public void setTheme(final String theme) {
		this.theme = theme;
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "AdminCommunication [theme=" + theme + ", text=" + text + ", userAccount=" + userFrom + ", getId()="
				+ getId() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

	@Override
	public void setUserFrom(IUserAccount userFrom) {
		this.userFrom = userFrom;
	}

	@Override
	public IUserAccount getUserFrom() {
		return userFrom;
	}

}
