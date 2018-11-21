package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;

public class AuctionRule extends BaseEntity implements IAuctionRule {

	private String index, theme, text;

	@Override
	public String getIndex() {
		return index;
	}

	@Override
	public void setIndex(final String index) {
		this.index = index;
	}

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
		return "AuctionRule [ordinalNumber=" + index + ", theme=" + theme + ", text=" + text + ", getId()="
				+ getId() + ", getCreated()=" + getCreated() + ", getUpdated()=" + getUpdated() + "]";
	}

}
