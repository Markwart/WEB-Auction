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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.DeferredBid;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.DeferredBid_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Item_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.UserAccount_;
import com.itacademy.jd2.mm.auction.daoapi.IDeferredBidDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.filter.DeferredBidFilter;

@Repository
public class DeferredBidDaoImpl extends AbstractDaoImpl<IDeferredBid, Integer> implements IDeferredBidDao {

	protected DeferredBidDaoImpl() {
		super(DeferredBid.class);
	}

	@Override
	public IDeferredBid createEntity() {
		final IDeferredBid deferredBid = new DeferredBid();
		return deferredBid;
	}

	@Override
	public IDeferredBid getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IDeferredBid> cq = cb.createQuery(IDeferredBid.class);
		final Root<DeferredBid> from = cq.from(DeferredBid.class);

		cq.select(from);

		from.fetch(DeferredBid_.userBid, JoinType.LEFT);
		from.fetch(DeferredBid_.item, JoinType.LEFT);

		cq.where(cb.equal(from.get(DeferredBid_.id), id));

		final TypedQuery<IDeferredBid> q = em.createQuery(cq);

		return getSingleResult(q);
	}
	
	@Override
	public List<IDeferredBid> find(DeferredBidFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IDeferredBid> cq = cb.createQuery(IDeferredBid.class);

		final Root<DeferredBid> from = cq.from(DeferredBid.class);

		cq.select(from);

		if (filter.getFetchUserAccount()) {
			from.fetch(DeferredBid_.userBid, JoinType.LEFT);
		}
		if (filter.getFetchItem()) {
			from.fetch(DeferredBid_.item, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IDeferredBid> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IDeferredBid> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(DeferredBidFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<DeferredBid> from = cq.from(DeferredBid.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
	private Path<?> getSortPath(final Root<DeferredBid> from, final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(DeferredBid_.created);
		case "updated":
			return from.get(DeferredBid_.updated);
		case "id":
			return from.get(DeferredBid_.id);
		case "price_bid":
			return from.get(DeferredBid_.priceBid);
		case "status_bid":
			return from.get(DeferredBid_.statusBid);
		case "item_id":
			return from.get(DeferredBid_.item).get(Item_.name);
		case "user_bid_id":
			return from.get(DeferredBid_.userBid).get(UserAccount_.email);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
	
	@Override
	public List<IDeferredBid> findRelatedDeferredBidsByItem(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IDeferredBid> cq = cb.createQuery(IDeferredBid.class);
		final Root<DeferredBid> from = cq.from(DeferredBid.class);
		
		cq.select(from);
		cq.where(cb.equal(from.get(DeferredBid_.item), id));

		final TypedQuery<IDeferredBid> q = em.createQuery(cq);
		final List<IDeferredBid> resultList = q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IDeferredBid> findRelatedDeferredBidsByUser(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IDeferredBid> cq = cb.createQuery(IDeferredBid.class);
		final Root<DeferredBid> from = cq.from(DeferredBid.class);

		cq.select(from);
		cq.where(cb.equal(from.get(DeferredBid_.userBid), id));

		final TypedQuery<IDeferredBid> q = em.createQuery(cq);
		final List<IDeferredBid> resultList = q.getResultList();
		return resultList;
	}
}
