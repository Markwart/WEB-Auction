package com.itacademy.jd2.mm.auction.web.dto.search;

import javax.validation.constraints.NotNull;

public class FeedbackSearchDTO {

	@NotNull
	private String userWhomEmail;

	public String getUserWhomEmail() {
		return userWhomEmail;
	}

	public void setUserWhomEmail(String userWhomEmail) {
		this.userWhomEmail = userWhomEmail;
	}

}
