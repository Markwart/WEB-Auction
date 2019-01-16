package com.itacademy.jd2.mm.auction.daoapi.filter;

public class MessageFilter extends AbstractFilter {
	
	private Integer LoggedUserId;

	private boolean fetchUserAccountFrom;
	private boolean fetchUserAccountWhom;
	private boolean fetchItem;
	
	public Integer getLoggedUserId() {
		return LoggedUserId;
	}
	public void setLoggedUserId(Integer loggedUserId) {
		LoggedUserId = loggedUserId;
	}
	public boolean getFetchUserAccountFrom() {
		return fetchUserAccountFrom;
	}
	public void setFetchUserAccountFrom(boolean fetchUserAccountFrom) {
		this.fetchUserAccountFrom = fetchUserAccountFrom;
	}
	public boolean getFetchUserAccountWhom() {
		return fetchUserAccountWhom;
	}
	public void setFetchUserAccountWhom(boolean fetchUserAccountWhom) {
		this.fetchUserAccountWhom = fetchUserAccountWhom;
	}
	public boolean getFetchItem() {
		return fetchItem;
	}
	public void setFetchItem(boolean fetchItem) {
		this.fetchItem = fetchItem;
	}

}
