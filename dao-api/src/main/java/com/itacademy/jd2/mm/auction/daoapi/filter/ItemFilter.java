package com.itacademy.jd2.mm.auction.daoapi.filter;

public class ItemFilter extends AbstractFilter {
	
	private String name, text;

	private boolean fetchUserAccount;
	private boolean fetchCategory;
	private boolean fetchCondition;
	private boolean fetchComposition;
	private boolean fetchCountryOrigin;

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

	public boolean getFetchUserAccount() {
		return fetchUserAccount;
	}

	public void setFetchUserAccount(boolean fetchUserAccount) {
		this.fetchUserAccount = fetchUserAccount;
	}

	public boolean getFetchCategory() {
		return fetchCategory;
	}

	public void setFetchCategory(boolean fetchCategory) {
		this.fetchCategory = fetchCategory;
	}

	public boolean getFetchCondition() {
		return fetchCondition;
	}

	public void setFetchCondition(boolean fetchCondition) {
		this.fetchCondition = fetchCondition;
	}

	public boolean getFetchComposition() {
		return fetchComposition;
	}

	public void setFetchComposition(boolean fetchComposition) {
		this.fetchComposition = fetchComposition;
	}

	public boolean getFetchCountryOrigin() {
		return fetchCountryOrigin;
	}

	public void setFetchCountryOrigin(boolean fetchCountryOrigin) {
		this.fetchCountryOrigin = fetchCountryOrigin;
	}

}
