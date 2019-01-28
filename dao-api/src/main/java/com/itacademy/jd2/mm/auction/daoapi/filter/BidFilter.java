package com.itacademy.jd2.mm.auction.daoapi.filter;

public class BidFilter extends AbstractFilter {
	
	private boolean fetchUserAccount;
	private boolean fetchItem;
	
	private Integer loggedUserId;
	
	public Integer getLoggedUserId() {
		return loggedUserId;
	}
	public void setLoggedUserId(Integer loggedUserId) {
		this.loggedUserId = loggedUserId;
	}
	private Integer itemId;
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
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
