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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Category;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Category_;
import com.itacademy.jd2.mm.auction.daoapi.ICategoryDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.daoapi.filter.CategoryFilter;

@Repository
public class CategoryDaoImpl extends AbstractDaoImpl<ICategory, Integer> implements ICategoryDao {

	protected CategoryDaoImpl() {
		super(Category.class);
	}

	@Override
	public ICategory createEntity() {
		final Category category = new Category();
		return category;
	}

	@Override
	public List<ICategory> find(CategoryFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final CriteriaQuery<ICategory> cq = cb.createQuery(ICategory.class); 

		final Root<Category> from = cq.from(Category.class);
		cq.select(from);

		if (filter.getSortColumn() != null) {
			final SingularAttribute<? super Category, ?> sortProperty = toMetamodelFormat(filter.getSortColumn());
			final Path<?> expression = from.get(sortProperty); 
																
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder())); 
		}

		final TypedQuery<ICategory> q = em.createQuery(cq);
		setPaging(filter, q);
		return q.getResultList();
	}

	@Override
	public long getCount(CategoryFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		
		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Category> from = cq.from(Category.class);
		
		cq.select(cb.count(from));
		
		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
	private SingularAttribute<? super Category, ?> toMetamodelFormat(final String sortColumn) {
        switch (sortColumn) {
        case "created":
            return Category_.created;
        case "updated":
            return Category_.updated;
        case "id":
            return Category_.id;
        case "name":
            return Category_.name;
        default:
            throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
        }
    }
}
