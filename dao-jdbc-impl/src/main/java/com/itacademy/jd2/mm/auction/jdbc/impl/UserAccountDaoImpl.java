package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IPersonalDataDao;
import com.itacademy.jd2.mm.auction.daoapi.IUserAccountDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IUserAccount;
import com.itacademy.jd2.mm.auction.daoapi.filter.UserAccountFilter;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class UserAccountDaoImpl extends AbstractDaoImpl<IUserAccount, Integer> implements IUserAccountDao {

	private IPersonalDataDao personalDataDao;

	@Autowired
	public UserAccountDaoImpl(IPersonalDataDao personalDataDao) {
		super();
		this.personalDataDao = personalDataDao;
	}

	@Override
	public IUserAccount createEntity() {
		return new UserAccount();
	}

	@Override
	public void update(IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(
				String.format("update %s set role=?, email=?, password=?, updated=? where id=?", getTableName())) {
			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getRole());
				pStmt.setString(2, entity.getEmail());
				pStmt.setString(3, entity.getPassword());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IUserAccount entity) {
		executeStatement(new PreparedStatementAction<IUserAccount>(String.format(
				"insert into %s (role, email, password, created, updated) values(?,?,?,?,?)", getTableName()), true) {

			@Override
			public IUserAccount doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getRole());
				pStmt.setString(2, entity.getEmail());
				pStmt.setString(3, entity.getPassword());
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
		return "user_account";
	}

	@Override
	protected IUserAccount parseRow(final ResultSet resultSet) throws SQLException {
		final IUserAccount entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setRole(resultSet.getInt("role"));
		entity.setEmail(resultSet.getString("email"));
		entity.setPassword(resultSet.getString("password"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public List<IUserAccount> find(UserAccountFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(UserAccountFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public IUserAccount getPersonalData(Integer id) {
		final IUserAccount userAccount = get(id);

		userAccount.setPersonalData(personalDataDao.get(id));
		return userAccount;
	}
}
