package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

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
		executeStatement(new PreparedStatementAction<IMessage>(
				String.format("update %s set item_id=?, user_from_id=?, user_whom_id=?, theme=?, text=?, updated=? where id=?",
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
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);
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
	protected IMessage parseRow(final ResultSet resultSet) throws SQLException {
		final IMessage entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setTheme(resultSet.getString("theme"));
		entity.setText(resultSet.getString("text"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Item item = new Item();
		item.setId((Integer) resultSet.getObject("item_id"));
		entity.setItem(item);

		final UserAccount userAccountFrom = new UserAccount();
		userAccountFrom.setId((Integer) resultSet.getObject("user_from_id"));
		entity.setUserAccountFrom(userAccountFrom);

		final UserAccount userAccountWhom = new UserAccount();
		userAccountWhom.setId((Integer) resultSet.getObject("user_whom_id"));
		entity.setUserAccountWhom(userAccountWhom);

		return entity;
	}

	@Override
	public List<IMessage> find(MessageFilter filter) {
		 final StringBuilder sqlTile = new StringBuilder("");
	        appendSort(filter, sqlTile);
	        appendPaging(filter, sqlTile);
	        return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(MessageFilter filter) {
		return executeCountQuery("");
	}
}
