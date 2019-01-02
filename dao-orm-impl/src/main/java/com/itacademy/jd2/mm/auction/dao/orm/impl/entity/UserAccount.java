package com.itacademy.jd2.mm.auction.dao.orm.impl.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.itacademy.jd2.mm.auction.dao.orm.PasswordUtils;
import com.itacademy.jd2.mm.auction.daoapi.entity.enums.Roles;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;

@Entity
public class UserAccount extends BaseEntity implements IUserAccount {
	
	@Column
	@Enumerated(EnumType.STRING)
	private Roles role;

	@Column
	private String email;

	@Column
	private String password;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "userAccount", targetEntity = PersonalData.class)
	private IPersonalData personalData;

	@JoinTable(name = "user_2_item", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "item_id") })
	@ManyToMany(targetEntity = Item.class, fetch = FetchType.LAZY)
	private Set<IItem> items = new HashSet<>();

	@Override
	public Roles getRole() {
		return role;
	}

	@Override
	public void setRole(Roles role) {
		this.role = role;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(final String email) {
		this.email = email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(final String password) {
		this.password = PasswordUtils.generateSecurePassword(password, PasswordUtils.getSalt(25));
	}

	@Override
	public IPersonalData getPersonalData() {
		return personalData;
	}

	@Override
	public void setPersonalData(IPersonalData personalData) {
		this.personalData = personalData;
	}

	public Set<IItem> getItems() {
		return items;
	}

	public void setItems(Set<IItem> items) {
		this.items = items;
	}
}
