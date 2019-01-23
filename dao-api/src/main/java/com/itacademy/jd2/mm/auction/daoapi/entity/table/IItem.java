package com.itacademy.jd2.mm.auction.daoapi.entity.table;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusAuction;

public interface IItem extends IBaseEntity {

	String getName();
	void setName(String name);

	Date getAuctionEnd();
	void setAuctionEnd(Date auctionEnd);

	BigDecimal getStartingPrice();
	void setStartingPrice(BigDecimal startingPrice);

	ICategory getCategory();
	void setCategory(ICategory category);

	Integer getYear();
	void setYear(Integer year);

	ICountryOrigin getCountryOrigin();
	void setCountryOrigin(ICountryOrigin countryOrigin);

	ICondition getCondition();
	void setCondition(ICondition condition);
	
	IComposition getComposition();
	void setComposition(IComposition composition);
	
	String getImage();
	void setImage(String string);
	
	String getText();
	void setText(String text);
	
	IUserAccount getSeller();
	void setSeller(IUserAccount seller);
	
	StatusAuction getStatusAuction();
	void setStatusAuction(StatusAuction statusAuction);
	
	IAuctionDuration getDuration();
	void setDuration(IAuctionDuration duration);
	
	void setPaymentMethods(Set<IPaymentMethod> paymentMethods);
	Set<IPaymentMethod> getPaymentMethods();
	
	void setShippingMethods(Set<IShippingMethod> shippingMethods);
	Set<IShippingMethod> getShippingMethods();
}
