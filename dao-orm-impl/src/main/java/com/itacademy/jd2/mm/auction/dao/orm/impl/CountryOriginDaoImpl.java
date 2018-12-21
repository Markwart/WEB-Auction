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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.CountryOrigin;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.CountryOrigin_;
import com.itacademy.jd2.mm.auction.daoapi.ICountryOriginDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.filter.CountryOriginFilter;

@Repository
public class CountryOriginDaoImpl extends AbstractDaoImpl<ICountryOrigin, Integer> implements ICountryOriginDao {

	protected CountryOriginDaoImpl() {
		super(CountryOrigin.class);
	}

	@Override
	public ICountryOrigin createEntity() {
		final CountryOrigin countryOrigin = new CountryOrigin();
		return countryOrigin;
	}

	@Override
	public List<ICountryOrigin> find(CountryOriginFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICountryOrigin> cq = cb.createQuery(ICountryOrigin.class); 

		final Root<CountryOrigin> from = cq.from(CountryOrigin.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super CountryOrigin, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); 
																
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); 
		}

		final TypedQuery<ICountryOrigin> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public long getCount(CountryOriginFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<CountryOrigin> from = cq.from(CountryOrigin.class);
		
		cq.select(cb.count(from));
		
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
	private SingularAttribute<? super CountryOrigin, ?> toMetamodelFormat(final String sortColumn) {
        switch (sortColumn) {
        case "created":
            return CountryOrigin_.created;
        case "updated":
            return CountryOrigin_.updated;
        case "id":
            return CountryOrigin_.id;
        case "name":
            return CountryOrigin_.name;
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }
}
