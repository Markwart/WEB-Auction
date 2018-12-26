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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.AuctionRule;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.AuctionRule_;
import com.itacademy.jd2.mm.auction.daoapi.IAuctionRuleDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionRuleFilter;

@Repository
public class AuctionRuleDaoImpl extends AbstractDaoImpl<IAuctionRule, Integer> implements IAuctionRuleDao {

	protected AuctionRuleDaoImpl() {
		super(AuctionRule.class);
	}

	@Override
	public IAuctionRule createEntity() {
		final AuctionRule auctionRule = new AuctionRule();
		return auctionRule;
	}

	@Override
	public List<IAuctionRule> find(AuctionRuleFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IAuctionRule> cq = cb.createQuery(IAuctionRule.class); 

		final Root<AuctionRule> from = cq.from(AuctionRule.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super AuctionRule, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); 
																
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); 
		}

		final TypedQuery<IAuctionRule> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public long getCount(AuctionRuleFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<AuctionRule> from = cq.from(AuctionRule.class);
		
		cq.select(cb.count(from));
		
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
	private SingularAttribute<? super AuctionRule, ?> toMetamodelFormat(final String sortColumn) {
        switch (sortColumn) {
        case "created":
            return AuctionRule_.created;
        case "updated":
            return AuctionRule_.updated;
        case "id":
            return AuctionRule_.id;
        case "index":
            return AuctionRule_.index;
        case "theme":
            return AuctionRule_.theme;
        case "text":
            return AuctionRule_.text;
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }
}
