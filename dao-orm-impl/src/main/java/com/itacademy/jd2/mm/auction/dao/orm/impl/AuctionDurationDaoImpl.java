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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.AuctionDuration;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.AuctionDuration_;
import com.itacademy.jd2.mm.auction.daoapi.IAuctionDurationDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionDurationFilter;

@Repository
public class AuctionDurationDaoImpl extends AbstractDaoImpl<IAuctionDuration, Integer> implements IAuctionDurationDao {

	protected AuctionDurationDaoImpl() {
		super(AuctionDuration.class);
	}

	@Override
	public IAuctionDuration createEntity() {
		final AuctionDuration auctionDuration = new AuctionDuration();
		return auctionDuration;
	}

	@Override
	public List<IAuctionDuration> find(AuctionDurationFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IAuctionDuration> cq = cb.createQuery(IAuctionDuration.class); 

		final Root<AuctionDuration> from = cq.from(AuctionDuration.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super AuctionDuration, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); 
																
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); 
		}

		final TypedQuery<IAuctionDuration> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public long getCount(AuctionDurationFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<AuctionDuration> from = cq.from(AuctionDuration.class);
		
		cq.select(cb.count(from));
		
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	private SingularAttribute<? super AuctionDuration, ?> toMetamodelFormat(final String sortColumn) {
        switch (sortColumn) {
        case "created":
            return AuctionDuration_.created;
        case "updated":
            return AuctionDuration_.updated;
        case "id":
            return AuctionDuration_.id;
        case "min":
            return AuctionDuration_.min;
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }
}
