package com.itacademy.jd2.mm.auction.web.dto.search;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemSearchDTO {
	
	@NotNull
	@Size(min=1)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
