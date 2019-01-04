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
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Bid;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Bid_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.DeferredBid;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.DeferredBid_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Feedback;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Feedback_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Item;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Item_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Message;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.Message_;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.UserAccount;
import com.itacademy.jd2.mm.auction.dao.orm.impl.entity.UserAccount_;
import com.itacademy.jd2.mm.auction.daoapi.IUserAccountDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.UserAccountFilter;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	protected UserAccountDaoImpl() {
		super(UserAccount.class);
	}

	@Override
	public IUserAccount createEntity() {
		final IUserAccount userAccount = new UserAccount();
		return userAccount;
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);

		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(from);

		from.fetch(UserAccount_.personalData, JoinType.LEFT);

		final String sortColumn = filter.getSortColumn();
		if (sortColumn != null) {
			final Path<?> expression = getSortPath(from, sortColumn);
			cq.orderBy(new OrderImpl(expression, filter.getSortOrder()));
		}

		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		setPaging(filter, q);
		final List<IUserAccount> resultList = q.getResultList();
		return resultList;
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(cb.count(from));

		final TypedQuery<Long> q = em.createQuery(cq);
		return q.getSingleResult();
	}

	@Override
	public IUserAccount getPersonalData(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(from);

		from.fetch(UserAccount_.personalData, JoinType.LEFT);
		cq.distinct(true);

		cq.where(cb.equal(from.get(UserAccount_.id), id));

		final TypedQuery<IUserAccount> q = em.createQuery(cq);

		return getSingleResult(q);
	}

	private Path<?> getSortPath(final Root<UserAccount> from, final String sortColumn) {
		switch (sortColumn) {
		case "created":
			return from.get(UserAccount_.created);
		case "updated":
			return from.get(UserAccount_.updated);
		case "id":
			return from.get(UserAccount_.id);
		case "email":
			return from.get(UserAccount_.email);
		case "password":
			return from.get(UserAccount_.password);
		case "role":
			return from.get(UserAccount_.role);
		default:
			throw new UnsupportedOperationException("sorting is not supported by column:" + sortColumn);
		}
	}

	@Override
	public IUserAccount getUserByLogin(String username) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IUserAccount> cq = cb.createQuery(IUserAccount.class);
		final Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(from);
		cq.where(cb.equal(from.get(UserAccount_.email), username));

		final TypedQuery<IUserAccount> q = em.createQuery(cq);
		return getSingleResult(q);
	}
	
	@Override
	public List<IBid> findRelatedBids(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IBid> cq = cb.createQuery(IBid.class);
		final Root<Bid> from = cq.from(Bid.class);
		
		cq.select(from);
		cq.where(cb.equal(from.get(Bid_.userBid), id));

		final TypedQuery<IBid> q = em.createQuery(cq);
		final List<IBid> resultList = q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IDeferredBid> findRelatedDeferredBids(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IDeferredBid> cq = cb.createQuery(IDeferredBid.class);
		final Root<DeferredBid> from = cq.from(DeferredBid.class);
		
		cq.select(from);
		cq.where(cb.equal(from.get(DeferredBid_.userBid), id));

		final TypedQuery<IDeferredBid> q = em.createQuery(cq);
		final List<IDeferredBid> resultList = q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IItem> findRelatedItems(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IItem> cq = cb.createQuery(IItem.class);
		final Root<Item> from = cq.from(Item.class);
		
		cq.select(from);
		cq.where(cb.equal(from.get(Item_.seller), id));

		final TypedQuery<IItem> q = em.createQuery(cq);
		final List<IItem> resultList = q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IFeedback> findRelatedFeedback(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IFeedback> cq = cb.createQuery(IFeedback.class);
		final Root<Feedback> from = cq.from(Feedback.class);
		
		cq.select(from);
		cq.where(cb.equal(from.get(Feedback_.userFrom), id));
		cq.where(cb.equal(from.get(Feedback_.userWhom), id));

		final TypedQuery<IFeedback> q = em.createQuery(cq);
		final List<IFeedback> resultList = q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IMessage> findRelatedMessages(Integer id) {
		final EntityManager em = getEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();

		final CriteriaQuery<IMessage> cq = cb.createQuery(IMessage.class);
		final Root<Message> from = cq.from(Message.class);
		
		cq.select(from);
		cq.where(cb.equal(from.get(Message_.userFrom), id));
		cq.where(cb.equal(from.get(Message_.userWhom), id));

		final TypedQuery<IMessage> q = em.createQuery(cq);
		final List<IMessage> resultList = q.getResultList();
		return resultList;
	}
	
	@Override
	public List<IAdminCommunication> findRelatedAdminCommunication(Integer id) {
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
