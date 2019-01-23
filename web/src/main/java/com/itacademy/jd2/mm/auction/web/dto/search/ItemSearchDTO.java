package com.itacademy.jd2.mm.auction.web.dto.search;

import javax.validation.constraints.NotNull;

public class ItemSearchDTO {
	
	@NotNull
	private String name, text;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
