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
	private IUserAccount userFrom, userWhom;

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
	public IItem getItem() {
		return item;
	}

	@Override
	public void setItem(IItem item) {
		this.item = item;
	}

	@Override
	public void setUserWhom(IUserAccount userWhom) {
		this.userWhom = userWhom;
	}

	@Override
	public IUserAccount getUserWhom() {
		return userWhom;
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
