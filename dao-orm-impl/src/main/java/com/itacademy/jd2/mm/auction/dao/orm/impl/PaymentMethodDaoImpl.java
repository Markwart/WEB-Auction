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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.PaymentMethod;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.PaymentMethod_;
import com.itacademy.jd2.mm.auction.daoapi.IPaymentMethodDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPaymentMethod;
import com.itacademy.jd2.mm.auction.daoapi.filter.PaymentMethodFilter;

@Repository
public class PaymentMethodDaoImpl extends AbstractDaoImpl<IPaymentMethod, Integer> implements IPaymentMethodDao {

	protected PaymentMethodDaoImpl() {
		super(PaymentMethod.class);
	}

	@Override
	public IPaymentMethod createEntity() {
		final PaymentMethod paymentMethod = new PaymentMethod();
		return paymentMethod;
	}

	@Override
	public List<IPaymentMethod> find(PaymentMethodFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IPaymentMethod> cq = cb.createQuery(IPaymentMethod.class);

		final Root<PaymentMethod> from = cq.from(PaymentMethod.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super PaymentMethod, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty);

			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IPaymentMethod> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public long getCount(PaymentMethodFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<PaymentMethod> from = cq.from(PaymentMethod.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private SingularAttribute<? super PaymentMethod, ?> toMetamodelFormat(final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return PaymentMethod_.created;
		case "updated":
			return PaymentMethod_.updated;
		case "id":
			return PaymentMethod_.id;
		case "name":
			return PaymentMethod_.name;
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
}
