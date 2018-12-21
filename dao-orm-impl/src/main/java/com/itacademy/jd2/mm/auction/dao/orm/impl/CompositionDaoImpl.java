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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Composition;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Composition_;
import com.itacademy.jd2.mm.auction.daoapi.ICompositionDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.daoapi.filter.CompositionFilter;

@Repository
public class CompositionDaoImpl extends AbstractDaoImpl<IComposition, Integer> implements ICompositionDao {

	protected CompositionDaoImpl() {
		super(Composition.class);
	}

	@Override
	public IComposition createEntity() {
		final Composition composition = new Composition();
		return composition;
	}

	@Override
	public List<IComposition> find(CompositionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IComposition> cq = cb.createQuery(IComposition.class); 

		final Root<Composition> from = cq.from(Composition.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Composition, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); 
																
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); 
		}

		final TypedQuery<IComposition> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public long getCount(CompositionFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Composition> from = cq.from(Composition.class);
		
		cq.select(cb.count(from));
		
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
	private SingularAttribute<? super Composition, ?> toMetamodelFormat(final String sortColumn) {
        switch (sortColumn) {
        case "created":
            return Composition_.created;
        case "updated":
            return Composition_.updated;
        case "id":
            return Composition_.id;
        case "name":
            return Composition_.name;
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }
}
