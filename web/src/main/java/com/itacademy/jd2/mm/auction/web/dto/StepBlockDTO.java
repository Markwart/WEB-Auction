package com.itacademy.jd2.mm.auction.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class StepBlockDTO {

	private Integer id;

	@Size(min = 1, max = 30)
	private String name;
	
	@NotNull
	private Integer step_1, step_2, step_3, step_4, step_5;
	
	private Date created;

	private Date updated;

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

	public Integer getStep_1() {
		return step_1;
	}

	public void setStep_1(Integer step_1) {
		this.step_1 = step_1;
	}

	public Integer getStep_2() {
		return step_2;
	}

	public void setStep_2(Integer step_2) {
		this.step_2 = step_2;
	}

	public Integer getStep_3() {
		return step_3;
	}

	public void setStep_3(Integer step_3) {
		this.step_3 = step_3;
	}

	public Integer getStep_4() {
		return step_4;
	}

	public void setStep_4(Integer step_4) {
		this.step_4 = step_4;
	}

	public Integer getStep_5() {
		return step_5;
	}

	public void setStep_5(Integer step_5) {
		this.step_5 = step_5;
	}

}
