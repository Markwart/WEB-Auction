package com.itacademy.jd2.mm.auction.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BidDTO {

	private Integer id;

	private BigDecimal priceBid;
	
	private String statusBid;
	
	private Integer userAccountId;
	
	private Integer itemId;

	private Date created;

	private Date updated;

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

	
	public Integer getUserAccountId() {
		return userAccountId;
	}

	public void setUserAccountId(Integer userAccountId) {
		this.userAccountId = userAccountId;
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
