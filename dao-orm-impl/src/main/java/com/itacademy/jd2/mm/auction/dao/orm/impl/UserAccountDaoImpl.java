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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.UserAccount;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.UserAccount_;
import com.itacademy.jd2.mm.auction.daoapi.IUserAccountDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.UserAccountFilter;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	protected UserAccountDaoImpl() {
		super(UserAccount.class);
	}

	@Override
	public IUserAccount createEntity() {
		final IUserAccount userAccount = new UserAccount();
		return userAccount;
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(from);
		from.fetch(UserAccount_.personalData, JoinType.LEFT);

		applyFilter(filter, cb, cq, from);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IUserAccount> resultList = q.getResultList();
		return resultList;
	}

	private void applyFilter(final UserAccountFilter filter, final CriteriaBuilder cb, final CriteriaQuery<?> cq,
			final Root<UserAccount> from) {
		final List<Predicate> ands = new ArrayList<>();

		final String email = filter.getEmail();
		if (!StringUtils.isEmpty(email)) {
			ands.add(cb.equal(from.get(UserAccount_.email), email));
		}
		if (!ands.isEmpty()) {
			cq.where(cb.and(ands.toArray(new Predicate[0])));
		}
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IUserAccount getPersonalData(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(from);

		from.fetch(UserAccount_.personalData, JoinType.LEFT);
		from.fetch(UserAccount_.items, JoinType.LEFT);
		cq.distinct(true); // to avoid duplicate rows in result

		cq.where(cb.equal(from.get(UserAccount_.id), id));

		final TypedQuery<IUserAccount> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	private Path<?> getSortPath(final Root<UserAccount> from, final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(UserAccount_.created);
		case "updated":
			return from.get(UserAccount_.updated);
		case "id":
			return from.get(UserAccount_.id);
		case "email":
			return from.get(UserAccount_.email);
		case "password":
			return from.get(UserAccount_.password);
		case "role":
			return from.get(UserAccount_.role);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public IUserAccount getUserByLogin(String username) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(from);
		cq.where(cb.equal(from.get(UserAccount_.email), username));

		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		return getSingleResult(q);
	}
}
