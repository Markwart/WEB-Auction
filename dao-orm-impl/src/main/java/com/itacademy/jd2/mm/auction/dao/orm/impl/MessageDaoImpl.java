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

import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Item_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Message;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Message_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.UserAccount_;
import com.itacademy.jd2.mm.auction.daoapi.IMessageDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.filter.MessageFilter;

@Repository
public class MessageDaoImpl extends AbstractDaoImpl<IMessage, Integer> implements IMessageDao {

	protected MessageDaoImpl() {
		super(Message.class);
	}

	@Override
	public IMessage createEntity() {
		final Message message = new Message();
		return message;
	}
	
	@Override
	public IMessage getFullInfo(final Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IMessage> cq = cb.createQuery(IMessage.class);
		final Root<Message> from = cq.from(Message.class);

		cq.select(from);

		from.fetch(Message_.item, JoinType.LEFT);
		from.fetch(Message_.userFrom, JoinType.LEFT);
		from.fetch(Message_.userWhom, JoinType.LEFT);

		cq.where(cb.equal(from.get(Message_.id), id));

		final TypedQuery<IMessage> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	@Override
	public List<IMessage> find(MessageFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IMessage> cq = cb.createQuery(IMessage.class);

		final Root<Message> from = cq.from(Message.class);

		cq.select(from);
		if (filter.getLoggedUserId() != null) {
			Predicate predicate1 = cb.equal(from.get(Message_.userFrom), filter.getLoggedUserId());
			Predicate predicate2 = cb.equal(from.get(Message_.userWhom), filter.getLoggedUserId());
			cq.where(cb.or(predicate1, predicate2));
		} // only for logged user

		if (filter.getFetchUserAccountFrom()) {
			from.fetch(Message_.userFrom, JoinType.LEFT);
		}
		if (filter.getFetchUserAccountWhom()) {
			from.fetch(Message_.userWhom, JoinType.LEFT);
		}
		if (filter.getFetchItem()) {
			from.fetch(Message_.item, JoinType.LEFT);
		}

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IMessage> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IMessage> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(MessageFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<Message> from = cq.from(Message.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}
	
	private Path<?> getSortPath(final Root<Message> from, final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(Message_.created);
		case "updated":
			return from.get(Message_.updated);
		case "id":
			return from.get(Message_.id);
		case "theme":
			return from.get(Message_.theme);
		case "text":
			return from.get(Message_.text);
		case "item_id":
			return from.get(Message_.item).get(Item_.name);
		case "user_from_id":
			return from.get(Message_.userFrom).get(UserAccount_.email);
		case "user_whom_id":
			return from.get(Message_.userWhom).get(UserAccount_.email);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}
	
	@Override
	public List<IMessage> findRelatedMessagesByItem(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IMessage> cq = cb.createQuery(IMessage.class);
		final Root<Message> from = cq.from(Message.class);
		
		cq.select(from);
		cq.where(cb.equal(from.get(Message_.item), id));

		final TypedQuery<IMessage> q = em.createQuery(cq);
		final List<IMessage> resultList = q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IMessage> findRelatedMessagesByUser(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IMessage> cq = cb.createQuery(IMessage.class);
		final Root<Message> from = cq.from(Message.class);

		cq.select(from);
		final List<Predicate> ands = new ArrayList<>();
		ands.add(cb.equal(from.get(Message_.userFrom), id));
		ands.add(cb.equal(from.get(Message_.userWhom), id));

		final TypedQuery<IMessage> q = em.createQuery(cq);
		final List<IMessage> resultList = q.getResultList();
		return resultList;
	}
}
