package com.itacademy.jd2.mm.auction.daoapi.filter;

public class ItemFilter extends AbstractFilter {

	private boolean fetchUserAccount;
	private boolean fetchCategory;
	private boolean fetchCondition;
	private boolean fetchComposition;
	private boolean fetchCountryOrigin;

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
