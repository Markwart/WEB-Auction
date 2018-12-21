package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;

@Entity
public class AuctionRule extends BaseEntity implements IAuctionRule {

	@Column
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
}
