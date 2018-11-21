package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IPersonalDataDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IPersonalData;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.PersonalData;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class PersonalDataDaoImpl extends AbstractDaoImpl<IPersonalData, Integer> implements IPersonalDataDao {

	@Override
	public IPersonalData createEntity() {
		return new PersonalData();
	}

	@Override
	public void update(IPersonalData entity) {
		executeStatement(new PreparedStatementAction<IPersonalData>(
				String.format("update %s set username=?, first_name=?, last_name=?, adress=?, updated=? where id=?", getTableName())) {
			@Override
			public IPersonalData doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getUsername());
				pStmt.setString(2, entity.getFirstName());
				pStmt.setString(3, entity.getLastName());
				pStmt.setString(4, entity.getAdress());
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(6, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});		
	}

	@Override
	public void insert(IPersonalData entity) {
		executeStatement(new PreparedStatementAction<IPersonalData>(
				String.format("insert into %s (id, username, first_name, last_name, adress, created, updated) values(?,?,?,?,?,?,?)", getTableName()), true) {

			@Override
			public IPersonalData doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getUserAccount().getId());
				pStmt.setString(2, entity.getUsername());
				pStmt.setString(3, entity.getFirstName());
				pStmt.setString(4, entity.getLastName());
				pStmt.setString(5, entity.getAdress());
				pStmt.setObject(6, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(7, entity.getUpdated(), Types.TIMESTAMP);

				pStmt.executeUpdate();
				return entity;
			}
		});		
	}

	@Override
	protected String getTableName() {
		return "personal_data";
	}

	@Override
	protected IPersonalData parseRow(final ResultSet resultSet) throws SQLException {
		final IPersonalData entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setUserName(resultSet.getString("username"));
		entity.setFirstName(resultSet.getString("first_name"));
		entity.setLastName(resultSet.getString("last_name"));
		entity.setAdress(resultSet.getString("adress"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}
}
