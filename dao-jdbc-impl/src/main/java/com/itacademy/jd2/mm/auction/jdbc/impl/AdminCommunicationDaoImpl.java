package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IAdminCommunicationDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAdminCommunication;
import com.itacademy.jd2.mm.auction.daoapi.filter.AdminCommunicationFilter;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.AdminCommunication;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class AdminCommunicationDaoImpl extends AbstractDaoImpl<IAdminCommunication, Integer>
		implements IAdminCommunicationDao {

	@Override
	public IAdminCommunication createEntity() {
		return new AdminCommunication();
	}

	@Override
	public void update(IAdminCommunication entity) {
		executeStatement(new PreparedStatementAction<IAdminCommunication>(
				String.format("update %s set theme=?, user_from_id=?, text=?, updated=? where id=?", getTableName())) {
			@Override
			public IAdminCommunication doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getTheme());
				pStmt.setInt(2, entity.getUserAccount().getId());
				pStmt.setString(3, entity.getText());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IAdminCommunication entity) {
		executeStatement(new PreparedStatementAction<IAdminCommunication>(
				String.format("insert into %s (theme, user_from_id, text, created, updated) values(?,?,?,?,?)",
						getTableName()),
				true) {

			@Override
			public IAdminCommunication doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getTheme());
				pStmt.setInt(2, entity.getUserAccount().getId());
				pStmt.setString(3, entity.getText());
				pStmt.setObject(4, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);

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
		return "admin_communication";
	}

	@Override
	protected IAdminCommunication parseRow(final ResultSet resultSet) throws SQLException {
		final IAdminCommunication entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setTheme(resultSet.getString("theme"));
		entity.setText(resultSet.getString("text"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final UserAccount userAccount = new UserAccount();
		userAccount.setId((Integer) resultSet.getObject("user_from_id"));
		entity.setUserAccount(userAccount);

		return entity;
	}

	@Override
	public List<IAdminCommunication> find(AdminCommunicationFilter filter) {
		 final StringBuilder sqlTile = new StringBuilder("");
	        appendSort(filter, sqlTile);
	        appendPaging(filter, sqlTile);
	        return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(AdminCommunicationFilter filter) {
		return executeCountQuery("");
	}
}
