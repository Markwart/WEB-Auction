package com.itacademy.jd2.mm.auction.daoapi.filter;

public class AdminCommunicationFilter extends AbstractFilter {

	private boolean fetchUserAccount;

	public boolean getFetchUserAccount() {
		return fetchUserAccount;
	}

	public void setFetchUserAccount(boolean fetchUserAccount) {
		this.fetchUserAccount = fetchUserAccount;
	}
}
