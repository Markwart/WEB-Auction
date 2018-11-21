package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

public class Message extends BaseEntity implements IMessage {

	private String theme, text;
	private IUserAccount userAccountFrom, userAccountWhom;
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

	@Override
	public String toString() {
		return "Message [theme=" + theme + ", text=" + text + ", userAccountFrom=" + userAccountFrom
				+ ", userAccountWhom=" + userAccountWhom + ", item=" + item + ", getId()=" + getId() + ", getCreated()="
				+ getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

}
