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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.ShippingMethod;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.ShippingMethod_;
import com.itacademy.jd2.mm.auction.daoapi.IShippingMethodDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.filter.ShippingMethodFilter;

@Repository
public class ShippingMethodDaoImpl extends AbstractDaoImpl<IShippingMethod, Integer> implements IShippingMethodDao {

	protected ShippingMethodDaoImpl() {
		super(ShippingMethod.class);
	}

	@Override
	public IShippingMethod createEntity() {
		final ShippingMethod shippingMethod = new ShippingMethod();
		return shippingMethod;
	}

	@Override
	public List<IShippingMethod> find(ShippingMethodFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IShippingMethod> cq = cb.createQuery(IShippingMethod.class); 

		final Root<ShippingMethod> from = cq.from(ShippingMethod.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super ShippingMethod, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); 
																
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); 
		}

		final TypedQuery<IShippingMethod> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public long getCount(ShippingMethodFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<ShippingMethod> from = cq.from(ShippingMethod.class);
		
		cq.select(cb.count(from));
		
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
	private SingularAttribute<? super ShippingMethod, ?> toMetamodelFormat(final String sortColumn) {
        switch (sortColumn) {
        case "created":
            return ShippingMethod_.created;
        case "updated":
            return ShippingMethod_.updated;
        case "id":
            return ShippingMethod_.id;
        case "name":
            return ShippingMethod_.name;
        case "delivery_time":
            return ShippingMethod_.deliveryTime;
        case "cost":
            return ShippingMethod_.cost;
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }
}
