package com.itacademy.jd2.mm.auction.daoapi.filter;

public class FeedbackFilter extends AbstractFilter{
	
	private Integer loggedUserId;

	private boolean fetchUserAccountFrom;
	private boolean fetchUserAccountWhom;
	private boolean fetchItem;
	
	public Integer getLoggedUserId() {
		return loggedUserId;
	}
	public void setLoggedUserId(Integer loggedUserId) {
		this.loggedUserId = loggedUserId;
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
