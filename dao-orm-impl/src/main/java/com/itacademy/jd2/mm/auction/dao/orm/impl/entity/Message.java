package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

@Entity
public class Message extends BaseEntity implements IMessage {

	@Column
	private String theme, text;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount userAccountFrom, userAccountWhom;
	
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Item.class)
	private IItem item;

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
	public IUserAccount getUserAccountFrom() {
		return userAccountFrom;
	}

	@Override
	public void setUserAccountFrom(final IUserAccount userAccountFrom) {
		this.userAccountFrom = userAccountFrom;
	}

	@Override
	public IUserAccount getUserAccountWhom() {
		return userAccountWhom;
	}

	@Override
	public void setUserAccountWhom(final IUserAccount userAccountWhom) {
		this.userAccountWhom = userAccountWhom;
	}

	@Override
	public IItem getItem() {
		return item;
	}

	@Override
	public void setItem(IItem item) {
		this.item = item;
	}
}
