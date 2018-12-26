package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Bid;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Bid_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Item_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.UserAccount_;
import com.itacademy.jd2.mm.auction.daoapi.IBidDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.filter.BidFilter;

@Repository
public class BidDaoImpl extends AbstractDaoImpl<IBid, Integer> implements IBidDao {

	protected BidDaoImpl() {
		super(Bid.class);
	}

	@Override
	public IBid createEntity() {
		final IBid bid = new Bid();
		return bid;
	}
	
	@Override
	public IBid getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IBid> cq = cb.createQuery(IBid.class);
		final Root<Bid> from = cq.from(Bid.class);

		cq.select(from);

		from.fetch(Bid_.userBid, JoinType.LEFT);
		from.fetch(Bid_.item, JoinType.LEFT);

		cq.where(cb.equal(from.get(Bid_.id), id));

		final TypedQuery<IBid> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IBid> find(BidFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IBid> cq = cb.createQuery(IBid.class);

		final Root<Bid> from = cq.from(Bid.class);

		cq.select(from);

		if (filter.getFetchUserAccount()) {
			from.fetch(Bid_.userBid, JoinType.LEFT);
		}
		if (filter.getFetchItem()) {
			from.fetch(Bid_.item, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IBid> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IBid> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(BidFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Bid> from = cq.from(Bid.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
	private Path<?> getSortPath(final Root<Bid> from, final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(Bid_.created);
		case "updated":
			return from.get(Bid_.updated);
		case "id":
			return from.get(Bid_.id);
		case "price_bid":
			return from.get(Bid_.priceBid);
		case "status_bid":
			return from.get(Bid_.statusBid);
		case "item_id":
			return from.get(Bid_.item).get(Item_.name);
		case "user_bid_id":
			return from.get(Bid_.userBid).get(UserAccount_.email);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
