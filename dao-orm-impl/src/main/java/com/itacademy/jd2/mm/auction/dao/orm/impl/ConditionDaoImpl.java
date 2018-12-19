package com.itacademy.jd2.mm.auction.dao.orm.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.hibernate.jpa.criteria.OrderImpl;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Condition;
import com.itacademy.jd2.mm.auction.daoapi.IConditionDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.daoapi.filter.ConditionFilter;

@Repository
public class ConditionDaoImpl extends AbstractDaoImpl<ICondition, Integer> implements IConditionDao {

	protected ConditionDaoImpl() {
		super(Condition.class);
	}

	@Override
	public ICondition createEntity() {
		final Condition condition = new Condition();
		return condition;
	}

	@Override
	public long getCount(ConditionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class); // define
																	// type of
		// result
		final Root<Condition> from = cq.from(Condition.class); // select from brand
		cq.select(cb.count(from)); // select what? select count(*)
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult(); // execute query
	}

	@Override
	public List<ICondition> find(ConditionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICondition> cq = cb.createQuery(ICondition.class); // define
		// type
		// of
		// result
		final Root<Condition> from = cq.from(Condition.class);// select from brand
		cq.select(from); // select what? select *

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Condition, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); // build path to
																// sort
			// property
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); // order
																			// by
			// column_name
			// order
		}

		final TypedQuery<ICondition> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	private SingularAttribute<? super Condition, ?> toMetamodelFormat(final String sortColumn) {
		return null;
		/*
		 * switch (sortColumn) { case "created": return Condition_.created; case
		 * "updated": return Condition_.updated; case "id": return Condition_.id; case
		 * "name": return Condition_.name; default: throw new
		 * UnsupportedOperationException("sorting is not supported by column:" +
		 * sortColumn);
		 */
	}
}
