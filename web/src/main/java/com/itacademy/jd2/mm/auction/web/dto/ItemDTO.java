package com.itacademy.jd2.mm.auction.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusAuction;

public class ItemDTO {

	private Integer id;
	
	private StatusAuction statusAuction;

	private String name, image, text;

	private Integer year;

	@DateTimeFormat(pattern = "yyyy-MM-dd") //hh:mm:ss a
	private Date auctionEnd;

	@NotNull
	@DecimalMin(value = "0.1")
	private BigDecimal startingPrice;

	@NotNull
	private Integer categoryId, countryOriginId, conditionId, compositionId, durationId;
	private String categoryName, countryOriginName, conditionName, compositionName;
	
	private Integer sellerId;
	private String sellerEmail;
	
	private Integer durationDay;

	private Date created;
	private Date updated;
	
	public StatusAuction getStatusAuction() {
		return statusAuction;
	}

	public void setStatusAuction(StatusAuction statusAuction) {
		this.statusAuction = statusAuction;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCountryOriginName() {
		return countryOriginName;
	}

	public void setCountryOriginName(String countryOriginName) {
		this.countryOriginName = countryOriginName;
	}

	public String getConditionName() {
		return conditionName;
	}

	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}

	public String getCompositionName() {
		return compositionName;
	}

	public void setCompositionName(String compositionName) {
		this.compositionName = compositionName;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(final Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(final Date updated) {
		this.updated = updated;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getDurationId() {
		return durationId;
	}

	public void setDurationId(Integer durationId) {
		this.durationId = durationId;
	}

	public Integer getDurationDay() {
		return durationDay;
	}

	public void setDurationDay(Integer durationDay) {
		this.durationDay = durationDay;
	}

	public Date getAuctionEnd() {
		return auctionEnd;
	}

	public void setAuctionEnd(Date auctionEnd) {
		this.auctionEnd = auctionEnd;
	}

	public BigDecimal getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(BigDecimal startingPrice) {
		this.startingPrice = startingPrice;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getCountryOriginId() {
		return countryOriginId;
	}

	public void setCountryOriginId(Integer countryOriginId) {
		this.countryOriginId = countryOriginId;
	}

	public Integer getConditionId() {
		return conditionId;
	}

	public void setConditionId(Integer conditionId) {
		this.conditionId = conditionId;
	}

	public Integer getCompositionId() {
		return compositionId;
	}

	public void setCompositionId(Integer compositionId) {
		this.compositionId = compositionId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

}
