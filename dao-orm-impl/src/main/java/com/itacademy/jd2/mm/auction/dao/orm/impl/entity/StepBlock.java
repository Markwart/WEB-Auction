package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;

@Entity
public class StepBlock extends BaseEntity implements IStepBlock {

	@Column
	private String name;

	@Column
	private Integer step_1, step_2, step_3, step_4, step_5;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Integer getStep_1() {
		return step_1;
	}

	@Override
	public void setStep_1(Integer step_1) {
		this.step_1 = step_1;
	}

	@Override
	public Integer getStep_2() {
		return step_2;
	}

	@Override
	public void setStep_2(Integer step_2) {
		this.step_2 = step_2;
	}

	@Override
	public Integer getStep_3() {
		return step_3;
	}

	@Override
	public void setStep_3(Integer step_3) {
		this.step_3 = step_3;
	}

	@Override
	public Integer getStep_4() {
		return step_4;
	}

	@Override
	public void setStep_4(Integer step_4) {
		this.step_4 = step_4;
	}

	@Override
	public Integer getStep_5() {
		return step_5;
	}

	@Override
	public void setStep_5(Integer step_5) {
		this.step_5 = step_5;
	}
}
