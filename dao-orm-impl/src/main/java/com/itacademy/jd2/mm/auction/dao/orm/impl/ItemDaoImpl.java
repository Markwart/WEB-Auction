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
		final Item item = new Item();
		return item;
	}

	@Override
	public List<IItem> find(ItemFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IItem> cq = cb.createQuery(IItem.class);

		final Root<Item> from = cq.from(Item.class);

		cq.select(from);

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

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IItem> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IItem> resultList = q.getResultList();
		return resultList;
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
		case "image":
			return from.get(Item_.image);
		case "text":
			return from.get(Item_.text);
		case "status_auction":
			return from.get(Item_.statusAuction);

		case "category":
			return from.get(Item_.category).get(Category_.name);
		case "country_origin":
			return from.get(Item_.countryOrigin).get(CountryOrigin_.name);
		case "condition":
			return from.get(Item_.condition).get(Condition_.name);
		case "composition":
			return from.get(Item_.composition).get(Composition_.name);
		case "seller_id":
			return from.get(Item_.seller).get(UserAccount_.email);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
