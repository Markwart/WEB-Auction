package com.itacademy.jd2.mm.auction.jdbc.impl.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

public class Item extends BaseEntity implements IItem {

	private String name, image, text, statusAuction;
	private Integer year;
	private Date auctionEnd;
	private BigDecimal startingPrice;
	private ICategory category;
	private ICountryOrigin countryOrigin;
	private ICondition condition;
	private IComposition composition;
	private IUserAccount seller;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public Date getAuctionEnd() {
		return auctionEnd;
	}

	@Override
	public void setAuctionEnd(final Date auctionEnd) {
		this.auctionEnd = auctionEnd;
	}

	@Override
	public BigDecimal getStartingPrice() {
		return startingPrice;
	}

	@Override
	public void setStartingPrice(final BigDecimal startingPrice) {
		this.startingPrice = startingPrice;
	}

	@Override
	public ICategory getCategory() {
		return category;
	}

	@Override
	public void setCategory(final ICategory category) {
		this.category = category;
	}

	@Override
	public Integer getYear() {
		return year;
	}

	@Override
	public void setYear(final Integer year) {
		this.year = year;
	}

	@Override
	public ICountryOrigin getCountryOrigin() {
		return countryOrigin;
	}

	@Override
	public void setCountryOrigin(final ICountryOrigin countryOrigin) {
		this.countryOrigin = countryOrigin;
	}

	@Override
	public ICondition getCondition() {
		return condition;
	}

	@Override
	public void setCondition(final ICondition condition) {
		this.condition = condition;
	}

	@Override
	public IComposition getComposition() {
		return composition;
	}

	@Override
	public void setComposition(final IComposition composition) {
		this.composition = composition;
	}

	@Override
	public String getImage() {
		return image;
	}

	@Override
	public void setImage(final String image) {
		this.image = image;
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
	public IUserAccount getSeller() {
		return seller;
	}

	@Override
	public void setSeller(final IUserAccount seller) {
		this.seller = seller;
	}

	@Override
	public String getStatusAuction() {
		return statusAuction;
	}

	@Override
	public void setStatusAuction(final String statusAuction) {
		this.statusAuction = statusAuction;
	}

}
