package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.jpa.criteria.OrderImpl;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.AuctionDuration_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Category_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Composition_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Condition_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.CountryOrigin_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Item;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Item_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.UserAccount_;
import com.itacademy.jd2.mm.auction.daoapi.IItemDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.filter.ItemFilter;

@Repository
public class ItemDaoImpl extends AbstractDaoImpl<IItem, Integer> implements IItemDao {

	protected ItemDaoImpl() {
		super(Item.class);
	}

	@Override
	public IItem createEntity() {
		final IItem item = new Item();
		return item;
	}

	@Override
	public IItem getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IItem> cq = cb.createQuery(IItem.class);
		final Root<Item> from = cq.from(Item.class);

		cq.select(from);

		from.fetch(Item_.category, JoinType.LEFT);
		from.fetch(Item_.composition, JoinType.LEFT);
		from.fetch(Item_.condition, JoinType.LEFT);
		from.fetch(Item_.countryOrigin, JoinType.LEFT);
		from.fetch(Item_.seller, JoinType.LEFT);
		from.fetch(Item_.duration, JoinType.LEFT);

		cq.where(cb.equal(from.get(Item_.id), id));

		final TypedQuery<IItem> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IItem> find(ItemFilter filter, Integer id) {

		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IItem> cq = cb.createQuery(IItem.class);
		final Root<Item> from = cq.from(Item.class);

		cq.select(from);
		if (id != null) {
			cq.where(cb.equal(from.get(Item_.seller), id));
		} // only for logged user

		if (filter.getFetchCategory()) {
			from.fetch(Item_.category, JoinType.LEFT);
		}
		if (filter.getFetchComposition()) {
			from.fetch(Item_.composition, JoinType.LEFT);
		}
		if (filter.getFetchCondition()) {
			from.fetch(Item_.condition, JoinType.LEFT);
		}
		if (filter.getFetchCountryOrigin()) {
			from.fetch(Item_.countryOrigin, JoinType.LEFT);
		}
		if (filter.getFetchUserAccount()) {
			from.fetch(Item_.seller, JoinType.LEFT);
		}
		if (filter.getFetchAuctionDuration()) {
			from.fetch(Item_.duration, JoinType.LEFT);
		}

		// applyFilter(filter, cb, cq, from);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IItem> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	/*
	 * private void applyFilter(final ItemFilter filter, final CriteriaBuilder cb,
	 * final CriteriaQuery<?> cq, final Root<Item> from) { final List<Predicate>
	 * ands = new ArrayList<>();
	 * 
	 * final String name = filter.getName(); if (!StringUtils.isEmpty(name)) {
	 * ands.add(cb.equal(from.get(Item_.name), name)); } if (!ands.isEmpty()) {
	 * cq.where(cb.and(ands.toArray(new Predicate[0]))); } }
	 */

	@Override
	public List<IItem> findInIndex(String text) {
		EntityManager em = getEntityManager();
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);

		// create native Lucene query unsing the query DSL
		// alternatively you can write the Lucene query using the Lucene query parser
		// or the Lucene programmatic API. The Hibernate Search DSL is recommended
		// though
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Item.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("text", "name").matching(text).createQuery();

		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Item.class);

		return jpaQuery.getResultList();
	}

	@Override
	public long getCount(ItemFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Item> from = cq.from(Item.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<Item> from, final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(Item_.created);
		case "updated":
			return from.get(Item_.updated);
		case "id":
			return from.get(Item_.id);
		case "name":
			return from.get(Item_.name);
		case "auction_end":
			return from.get(Item_.auctionEnd);
		case "starting_price":
			return from.get(Item_.startingPrice);
		case "year":
			return from.get(Item_.year);
		case "duration_id":
			return from.get(Item_.duration).get(AuctionDuration_.day);
		case "image":
			return from.get(Item_.image);
		case "text":
			return from.get(Item_.text);
		case "status_auction":
			return from.get(Item_.statusAuction);
		case "category_id":
			return from.get(Item_.category).get(Category_.name);
		case "country_origin_id":
			return from.get(Item_.countryOrigin).get(CountryOrigin_.name);
		case "condition_id":
			return from.get(Item_.condition).get(Condition_.name);
		case "composition_id":
			return from.get(Item_.composition).get(Composition_.name);
		case "seller_id":
			return from.get(Item_.seller).get(UserAccount_.email);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public List<IItem> findRelatedItemsBySeller(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IItem> cq = cb.createQuery(IItem.class);
		final Root<Item> from = cq.from(Item.class);

		cq.select(from);
		cq.where(cb.equal(from.get(Item_.seller), id));

		final TypedQuery<IItem> q = em.createQuery(cq);
		final List<IItem> resultList = q.getResultList();
		return resultList;
	}
}
