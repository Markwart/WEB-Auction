package com.itacademy.jd2.mm.auction.web.dto;

import java.util.Date;

public class FeedbackDTO {

	private Integer id;

	private Integer communication, shippingTime, shippingCharges, itemDescription;
	
	private String comment;
	
	private Integer userAccountFromId, userAccountWhomId;
	
	private Integer itemId;

	private Date created;

	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getCommunication() {
		return communication;
	}

	public void setCommunication(Integer communication) {
		this.communication = communication;
	}

	public Integer getShippingTime() {
		return shippingTime;
	}

	public void setShippingTime(Integer shippingTime) {
		this.shippingTime = shippingTime;
	}

	public Integer getShippingCharges() {
		return shippingCharges;
	}

	public void setShippingCharges(Integer shippingCharges) {
		this.shippingCharges = shippingCharges;
	}

	public Integer getItemDescription() {
		return itemDescription;
	}

	public void setItemDescription(Integer itemDescription) {
		this.itemDescription = itemDescription;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getUserAccountFromId() {
		return userAccountFromId;
	}

	public void setUserAccountFromId(Integer userAccountFromId) {
		this.userAccountFromId = userAccountFromId;
	}

	public Integer getUserAccountWhomId() {
		return userAccountWhomId;
	}

	public void setUserAccountWhomId(Integer userAccountWhomId) {
		this.userAccountWhomId = userAccountWhomId;
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
