package com.itacademy.jd2.mm.auction.web.dto;

import java.util.Date;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class AuctionDurationDTO {

	private Integer id;

	@NotNull
	@DecimalMin(value = "1")
	@DecimalMax(value = "30")
	private Integer day;

	private Date created;
	private Date updated;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
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
