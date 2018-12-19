package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IMessageDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IMessage;
import com.itacademy.jd2.mm.auction.daoapi.filter.MessageFilter;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Item;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Message;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class MessageDaoImpl extends AbstractDaoImpl<IMessage, Integer> implements IMessageDao {

	@Override
	public IMessage createEntity() {
		return new Message();
	}

	@Override
	public void update(IMessage entity) {
		executeStatement(new PreparedStatementAction<IMessage>(String.format(
				"update %s set item_id=?, user_from_id=?, user_whom_id=?, theme=?, text=?, updated=? where id=?",
				getTableName())) {
			@Override
			public IMessage doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getItem().getId());
				pStmt.setInt(2, entity.getUserAccountFrom().getId());
				pStmt.setInt(3, entity.getUserAccountWhom().getId());
				pStmt.setString(4, entity.getTheme());
				pStmt.setString(5, entity.getText());
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(7, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IMessage entity) {
		executeStatement(new PreparedStatementAction<IMessage>(String.format(
				"insert into %s (item_id, user_from_id, user_whom_id, theme, text, created, updated) values(?,?,?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IMessage doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getItem().getId());
				pStmt.setInt(2, entity.getUserAccountFrom().getId());
				pStmt.setInt(3, entity.getUserAccountWhom().getId());
				pStmt.setString(4, entity.getTheme());
				pStmt.setString(5, entity.getText());
				pStmt.setObject(6, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(7, entity.getUpdated(), Types.TIMESTAMP);

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
		return "message";
	}

	@Override
	protected IMessage parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IMessage entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setTheme(resultSet.getString("theme"));
		entity.setText(resultSet.getString("text"));
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
			entity.setUserAccountFrom(userAccountFrom);
		}

		final Integer userAccountWhomId = (Integer) resultSet.getObject("user_whom_id");
		if (userAccountWhomId != null) {
			final UserAccount userAccountWhom = new UserAccount();
			userAccountWhom.setId(userAccountWhomId);
			if (columns.contains("user_whom_email")) {
				userAccountWhom.setEmail(resultSet.getString("user_whom_email"));
			}
			entity.setUserAccountWhom(userAccountWhom);
		}
		return entity;
	}

	@Override
	public List<IMessage> find(MessageFilter filter) {
		final StringBuilder sqlTile;
		if (filter.getFetchUserAccountFrom() & filter.getFetchItem()) {
			sqlTile = new StringBuilder(String.format(
					"select message.*, user_account.email as user_from_email, item.name as item_name from %s",
					getTableName()));
		} else {
			sqlTile = new StringBuilder(String.format("message.* from %s", getTableName()));
		}

		appendJOINs(sqlTile, filter);
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQueryWithCustomSelect(sqlTile.toString());
	}

	private void appendJOINs(final StringBuilder sb, final MessageFilter filter) {
		if (filter.getFetchUserAccountFrom() & filter.getFetchItem()) {
			sb.append(" join user_account on (user_account.id=message.user_from_id) ")
					.append(" join item on (item.id=message.item_id) ");
		}
	}

	@Override
	public long getCount(MessageFilter filter) {
		return executeCountQuery("");
	}
}
