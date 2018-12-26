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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.StepBlock;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.StepBlock_;
import com.itacademy.jd2.mm.auction.daoapi.IStepBlockDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;
import com.itacademy.jd2.mm.auction.daoapi.filter.StepBlockFilter;

@Repository
public class StepBlockDaoImpl extends AbstractDaoImpl<IStepBlock, Integer> implements IStepBlockDao {

	protected StepBlockDaoImpl() {
		super(StepBlock.class);
	}

	@Override
	public IStepBlock createEntity() {
		final StepBlock stepBlock = new StepBlock();
		return stepBlock;
	}

	@Override
	public List<IStepBlock> find(StepBlockFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<IStepBlock> cq = cb.createQuery(IStepBlock.class); 

		final Root<StepBlock> from = cq.from(StepBlock.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super StepBlock, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); 
																
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); 
		}

		final TypedQuery<IStepBlock> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public long getCount(StepBlockFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<StepBlock> from = cq.from(StepBlock.class);
		
		cq.select(cb.count(from));
		
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
	private SingularAttribute<? super StepBlock, ?> toMetamodelFormat(final String sortColumn) {
        switch (sortColumn) {
        case "created":
            return StepBlock_.created;
        case "updated":
            return StepBlock_.updated;
        case "id":
            return StepBlock_.id;
        case "name":
            return StepBlock_.name;
        case "step_1":
            return StepBlock_.step_1;
        case "step_2":
            return StepBlock_.step_2;
        case "step_3":
            return StepBlock_.step_3;
        case "step_4":
            return StepBlock_.step_4;
        case "step_5":
            return StepBlock_.step_5;
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }
}
