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
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Feedback;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Feedback_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Item_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.UserAccount_;
import com.itacademy.jd2.mm.auction.daoapi.IFeedbackDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.filter.FeedbackFilter;

@Repository
public class FeedbackDaoImpl extends AbstractDaoImpl<IFeedback, Integer> implements IFeedbackDao {

	protected FeedbackDaoImpl() {
		super(Feedback.class);
	}

	@Override
	public IFeedback createEntity() {
		final Feedback feedback = new Feedback();
		return feedback;
	}

	@Override
	public IFeedback getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IFeedback> cq = cb.createQuery(IFeedback.class);
		final Root<Feedback> from = cq.from(Feedback.class);

		cq.select(from);

		from.fetch(Feedback_.item, JoinType.LEFT);
		from.fetch(Feedback_.userFrom, JoinType.LEFT);
		from.fetch(Feedback_.userWhom, JoinType.LEFT);

		cq.where(cb.equal(from.get(Feedback_.id), id));

		final TypedQuery<IFeedback> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IFeedback> find(FeedbackFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IFeedback> cq = cb.createQuery(IFeedback.class);
		final Root<Feedback> from = cq.from(Feedback.class);

		cq.select(from);
		if (filter.getLoggedUserId() != null) {
			Predicate predicate1 = cb.equal(from.get(Feedback_.userFrom), filter.getLoggedUserId());
			Predicate predicate2 = cb.equal(from.get(Feedback_.userWhom), filter.getLoggedUserId());
			cq.where(cb.or(predicate1, predicate2));
		} // only for logged user

		if (filter.getFetchUserAccountFrom()) {
			from.fetch(Feedback_.userFrom, JoinType.LEFT);
		}
		if (filter.getFetchUserAccountWhom()) {
			from.fetch(Feedback_.userWhom, JoinType.LEFT);
		}
		if (filter.getFetchItem()) {
			from.fetch(Feedback_.item, JoinType.LEFT);
		}

		applyFilter(filter, cb, cq, from);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IFeedback> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IFeedback> resultList = q.getResultList();
		return resultList;
	}

	private void applyFilter(final FeedbackFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
			final Root<Feedback> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String email = filter.getUserWhomEmail();
		if (!StringUtils.isEmpty(email)) {
			ands.add(cb.equal(from.get(Feedback_.userWhom).get(UserAccount_.email), email));
		}
		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}

	@Override
	public long getCount(FeedbackFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Feedback> from = cq.from(Feedback.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<Feedback> from, final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(Feedback_.created);
		case "updated":
			return from.get(Feedback_.updated);
		case "id":
			return from.get(Feedback_.id);
		case "communication":
			return from.get(Feedback_.communication);
		case "shipping_time":
			return from.get(Feedback_.shippingTime);
		case "shipping_charges":
			return from.get(Feedback_.shippingCharges);
		case "item_description":
			return from.get(Feedback_.itemDescription);
		case "comment":
			return from.get(Feedback_.comment);
		case "item_id":
			return from.get(Feedback_.item).get(Item_.name);
		case "user_from_id":
			return from.get(Feedback_.userFrom).get(UserAccount_.email);
		case "user_whom_id":
			return from.get(Feedback_.userWhom).get(UserAccount_.email);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public List<IFeedback> findRelatedFeedbackByItem(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IFeedback> cq = cb.createQuery(IFeedback.class);
		final Root<Feedback> from = cq.from(Feedback.class);

		cq.select(from);
		cq.where(cb.equal(from.get(Feedback_.item), id));

		final TypedQuery<IFeedback> q = em.createQuery(cq);
		final List<IFeedback> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public List<IFeedback> findRelatedFeedbackByUser(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IFeedback> cq = cb.createQuery(IFeedback.class);
		final Root<Feedback> from = cq.from(Feedback.class);

		cq.select(from);

		final List<Predicate> ands = new ArrayList<>();
		ands.add(cb.equal(from.get(Feedback_.userFrom), id));
		ands.add(cb.equal(from.get(Feedback_.userWhom), id));

		final TypedQuery<IFeedback> q = em.createQuery(cq);
		final List<IFeedback> resultList = q.getResultList();
		return resultList;
	}
}
