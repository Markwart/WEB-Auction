package com.itacademy.jd2.mm.auction.daoapi.filter;

public class BidFilter extends AbstractFilter {
	
	private boolean fetchUserAccount;
	private boolean fetchItem;
	
	public boolean getFetchUserAccount() {
		return fetchUserAccount;
	}
	public void setFetchUserAccount(boolean fetchUserAccount) {
		this.fetchUserAccount = fetchUserAccount;
	}
	public boolean getFetchItem() {
		return fetchItem;
	}
	public void setFetchItem(boolean fetchItem) {
		this.fetchItem = fetchItem;
	}

}
