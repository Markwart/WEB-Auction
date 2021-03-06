package com.itacademy.jd2.mm.auction.web.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class DeferredBidDTO {

	private Integer id;

	@NotNull
	@DecimalMin(value = "0.1")
	private BigDecimal priceBid;
	
	private String statusBid;
	
	@NotNull
	private Integer userBidId;
	private String userBidEmail;
	@NotNull
	private Integer itemId;
	private String itemName;

	private Date created;
	private Date updated;

	public Integer getUserBidId() {
		return userBidId;
	}

	public void setUserBidId(Integer userBidId) {
		this.userBidId = userBidId;
	}

	public String getUserBidEmail() {
		return userBidEmail;
	}

	public void setUserBidEmail(String userBidEmail) {
		this.userBidEmail = userBidEmail;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public BigDecimal getPriceBid() {
		return priceBid;
	}

	public void setPriceBid(BigDecimal priceBid) {
		this.priceBid = priceBid;
	}

	public String getStatusBid() {
		return statusBid;
	}

	public void setStatusBid(String statusBid) {
		this.statusBid = statusBid;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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

}
