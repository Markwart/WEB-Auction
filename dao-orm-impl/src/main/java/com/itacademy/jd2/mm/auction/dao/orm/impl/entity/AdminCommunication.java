package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

@Entity
public class AdminCommunication extends BaseEntity implements IAdminCommunication {

	@Column
	private String theme, text;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
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
	public IUserAccount getUserFrom() {
		return userFrom;
	}

	@Override
	public void setUserFrom(IUserAccount userFrom) {
		this.userFrom = userFrom;
	}

}
