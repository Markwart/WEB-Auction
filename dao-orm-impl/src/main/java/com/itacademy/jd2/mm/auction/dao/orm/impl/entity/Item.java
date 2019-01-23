package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusAuction;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

@Entity
@Indexed
public class Item extends BaseEntity implements IItem {

	@Column
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String name, text;

	@Column
	private String image;
	@Column
	private Integer year;
	@Column
	private Date auctionEnd;
	@Column
	private BigDecimal startingPrice;

	@Column
	@Enumerated(EnumType.STRING)
	private StatusAuction statusAuction;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class)
	private ICategory category;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = CountryOrigin.class)
	private ICountryOrigin countryOrigin;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Condition.class)
	private ICondition condition;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Composition.class)
	private IComposition composition;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = UserAccount.class)
	private IUserAccount seller;

	@ManyToOne(fetch = FetchType.LAZY, targetEntity = AuctionDuration.class)
	private IAuctionDuration duration;

	@JoinTable(name = "item_2_shipping_method", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = {
			@JoinColumn(name = "shipping_method_id") })
	@ManyToMany(targetEntity = ShippingMethod.class, fetch = FetchType.LAZY)
	private Set<IShippingMethod> shippingMethods = new HashSet<>();

	@JoinTable(name = "item_2_payment_method", joinColumns = { @JoinColumn(name = "item_id") }, inverseJoinColumns = {
			@JoinColumn(name = "payment_method_id") })
	@ManyToMany(targetEntity = PaymentMethod.class, fetch = FetchType.LAZY)
	private Set<IPaymentMethod> paymentMethods = new HashSet<>();

	@Override
	public Set<IShippingMethod> getShippingMethods() {
		return shippingMethods;
	}

	@Override
	public void setShippingMethods(Set<IShippingMethod> shippingMethods) {
		this.shippingMethods = shippingMethods;
	}

	@Override
	public Set<IPaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	@Override
	public void setPaymentMethods(Set<IPaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	@Override
	public IAuctionDuration getDuration() {
		return duration;
	}

	@Override
	public void setDuration(IAuctionDuration duration) {
		this.duration = duration;
	}

	@Override
	public StatusAuction getStatusAuction() {
		return statusAuction;
	}

	@Override
	public void setStatusAuction(StatusAuction statusAuction) {
		this.statusAuction = statusAuction;
	}

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
}
