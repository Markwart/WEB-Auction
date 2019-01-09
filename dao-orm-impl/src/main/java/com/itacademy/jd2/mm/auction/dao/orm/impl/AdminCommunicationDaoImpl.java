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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.AdminCommunication;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.AdminCommunication_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.UserAccount_;
import com.itacademy.jd2.mm.auction.daoapi.IAdminCommunicationDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.filter.AdminCommunicationFilter;

@Repository
public class AdminCommunicationDaoImpl extends AbstractDaoImpl<IAdminCommunication, Integer>
		implements IAdminCommunicationDao {

	protected AdminCommunicationDaoImpl() {
		super(AdminCommunication.class);
	}

	@Override
	public IAdminCommunication createEntity() {
		final IAdminCommunication adminCommunication = new AdminCommunication();
		return adminCommunication;
	}
	
	@Override
	public IAdminCommunication getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IAdminCommunication> cq = cb.createQuery(IAdminCommunication.class);
		final Root<AdminCommunication> from = cq.from(AdminCommunication.class);

		cq.select(from);

		from.fetch(AdminCommunication_.userFrom, JoinType.LEFT);

		cq.where(cb.equal(from.get(AdminCommunication_.id), id));

		final TypedQuery<IAdminCommunication> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IAdminCommunication> find(AdminCommunicationFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IAdminCommunication> cq = cb.createQuery(IAdminCommunication.class);

		final Root<AdminCommunication> from = cq.from(AdminCommunication.class);

		cq.select(from);

		if (filter.getFetchUserAccount()) {
			from.fetch(AdminCommunication_.userFrom, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IAdminCommunication> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IAdminCommunication> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(AdminCommunicationFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<AdminCommunication> from = cq.from(AdminCommunication.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private Path<?> getSortPath(final Root<AdminCommunication> from, final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(AdminCommunication_.created);
		case "updated":
			return from.get(AdminCommunication_.updated);
		case "id":
			return from.get(AdminCommunication_.id);
		case "theme":
			return from.get(AdminCommunication_.theme);
		case "text":
			return from.get(AdminCommunication_.text);
		case "user_from_id":
			return from.get(AdminCommunication_.userFrom).get(UserAccount_.email);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
	
	@Override
	public List<IAdminCommunication> findRelatedAdminCommunicationByUser(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IAdminCommunication> cq = cb.createQuery(IAdminCommunication.class);
		final Root<AdminCommunication> from = cq.from(AdminCommunication.class);

		cq.select(from);
		cq.where(cb.equal(from.get(AdminCommunication_.userFrom), id));

		final TypedQuery<IAdminCommunication> q = em.createQuery(cq);
		final List<IAdminCommunication> resultList = q.getResultList();
		return resultList;
	}
}
