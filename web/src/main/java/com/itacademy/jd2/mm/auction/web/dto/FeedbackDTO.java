package com.itacademy.jd2.mm.auction.web.dto;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class FeedbackDTO {

	private Integer id;

	@NotNull
	@DecimalMin(value = "1")
	@DecimalMax(value = "5")
	private Integer communication, shippingTime, shippingCharges, itemDescription;
	
	@NotNull
	private String comment;
	
	@NotNull
	private Integer userFromId, userWhomId;
	private String userFromEmail, userWhomEmail;
	@NotNull
	private Integer itemId;
	private String itemName;

	private Date created;
	private Date updated;

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

	public Integer getUserFromId() {
		return userFromId;
	}

	public void setUserFromId(Integer userFromId) {
		this.userFromId = userFromId;
	}

	public Integer getUserWhomId() {
		return userWhomId;
	}

	public void setUserWhomId(Integer userWhomId) {
		this.userWhomId = userWhomId;
	}

	public String getUserFromEmail() {
		return userFromEmail;
	}

	public void setUserFromEmail(String userFromEmail) {
		this.userFromEmail = userFromEmail;
	}

	public String getUserWhomEmail() {
		return userWhomEmail;
	}

	public void setUserWhomEmail(String userWhomEmail) {
		this.userWhomEmail = userWhomEmail;
	}

}
