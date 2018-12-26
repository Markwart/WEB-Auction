package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IFeedbackDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IFeedback;
import com.itacademy.jd2.mm.auction.daoapi.filter.FeedbackFilter;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Feedback;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Item;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class FeedbackDaoImpl extends AbstractDaoImpl<IFeedback, Integer> implements IFeedbackDao {

	@Override
	public IFeedback createEntity() {
		return new Feedback();
	}

	@Override
	public void update(IFeedback entity) {
		executeStatement(new PreparedStatementAction<IFeedback>(String.format(
				"update %s set item_id=?, user_from_id=?, user_whom_id=?, communication=?, shipping_time=?, shipping_charges=?, item_description=?, comment=?, updated=? where id=?",
				getTableName())) {
			@Override
			public IFeedback doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getItem().getId());
				pStmt.setInt(2, entity.getUserFrom().getId());
				pStmt.setInt(3, entity.getUserWhom().getId());
				pStmt.setInt(4, entity.getCommunication());
				pStmt.setInt(5, entity.getShippingTime());
				pStmt.setInt(6, entity.getShippingCharges());
				pStmt.setInt(7, entity.getItemDescription());
				pStmt.setString(8, entity.getComment());
				pStmt.setObject(9, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(10, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IFeedback entity) {
		executeStatement(new PreparedStatementAction<IFeedback>(String.format(
				"insert into %s (item_id, user_from_id, user_whom_id, communication, shipping_time, shipping_charges, item_description, comment, created, updated) values(?,?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IFeedback doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getItem().getId());
				pStmt.setInt(2, entity.getUserFrom().getId());
				pStmt.setInt(3, entity.getUserWhom().getId());
				pStmt.setInt(4, entity.getCommunication());
				pStmt.setInt(5, entity.getShippingTime());
				pStmt.setInt(6, entity.getShippingCharges());
				pStmt.setInt(7, entity.getItemDescription());
				pStmt.setString(8, entity.getComment());
				pStmt.setObject(9, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(10, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();

				final ResultSet rs = pStmt.getGeneratedKeys();
				rs.next();
				final int id = rs.getInt("id");

				rs.close();

				entity.setId(id);
				return entity;
			}
		});
	}

	@Override
	protected String getTableName() {
		return "feedback";
	}

	@Override
	protected IFeedback parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IFeedback entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setCommunication(resultSet.getInt("communication"));
		entity.setShippingTime(resultSet.getInt("shipping_time"));
		entity.setShippingCharges(resultSet.getInt("shipping_charges"));
		entity.setItemDescription(resultSet.getInt("item_description"));
		entity.setComment(resultSet.getString("comment"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer itemId = (Integer) resultSet.getObject("item_id");
		if (itemId != null) {
			final Item item = new Item();
			item.setId(itemId);
			if (columns.contains("item_name")) {
				item.setName(resultSet.getString("item_name"));
			}
			entity.setItem(item);
		}

		final Integer userAccountFromId = (Integer) resultSet.getObject("user_from_id");
		if (userAccountFromId != null) {
			final UserAccount userAccountFrom = new UserAccount();
			userAccountFrom.setId(userAccountFromId);
			if (columns.contains("user_from_email")) {
				userAccountFrom.setEmail(resultSet.getString("user_from_email"));
			}
			entity.setUserFrom(userAccountFrom);
		}

		final Integer userAccountWhomId = (Integer) resultSet.getObject("user_whom_id");
		if (userAccountWhomId != null) {
			final UserAccount userAccountWhom = new UserAccount();
			userAccountWhom.setId(userAccountWhomId);
			if (columns.contains("user_whom_email")) {
				userAccountWhom.setEmail(resultSet.getString("user_whom_email"));
			}
			entity.setUserWhom(userAccountWhom);
		}
		return entity;
	}

	@Override
	public List<IFeedback> find(FeedbackFilter filter) {
		final StringBuilder sqlTile;
		if (filter.getFetchUserAccountFrom() & filter.getFetchUserAccountWhom() & filter.getFetchItem()) {
			sqlTile = new StringBuilder(String.format(
					"select feedback.*, user_account.email as user_from_email, item.name as item_name from %s",
					getTableName()));
		} else {
			sqlTile = new StringBuilder(String.format("feedback.* from %s", getTableName()));
		}

		appendJOINs(sqlTile, filter);
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQueryWithCustomSelect(sqlTile.toString());
	}

	private void appendJOINs(final StringBuilder sb, final FeedbackFilter filter) {
		if (filter.getFetchUserAccountFrom() & filter.getFetchUserAccountWhom() & filter.getFetchItem()) {
			sb.append(" join user_account on (user_account.id=feedback.user_from_id) ")
					.append(" join item on (item.id=feedback.item_id) ");
		}
	}

	@Override
	public long getCount(FeedbackFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public IFeedback getFullInfo(Integer id) {
		throw new RuntimeException("not implemneted");
	}
}
