package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.ICountryOriginDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICountryOrigin;
import com.itacademy.jd2.mm.auction.daoapi.filter.CountryOriginFilter;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.CountryOrigin;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class CountryOriginDaoImpl extends AbstractDaoImpl<ICountryOrigin, Integer> implements ICountryOriginDao{

	@Override
	public ICountryOrigin createEntity() {
		return new CountryOrigin();
	}

	@Override
	public void update(ICountryOrigin entity) {
		executeStatement(new PreparedStatementAction<ICountryOrigin>(
				String.format("update %s set name=?, updated=? where id=?", getTableName())) {
			@Override
			public ICountryOrigin doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(ICountryOrigin entity) {
		executeStatement(new PreparedStatementAction<ICountryOrigin>(
				String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()), true) {

			@Override
			public ICountryOrigin doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

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
		return "country_origin";
	}
	
	@Override
	protected ICountryOrigin parseRow(final ResultSet resultSet) throws SQLException {
		final ICountryOrigin entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public List<ICountryOrigin> find(CountryOriginFilter filter) {
		 final StringBuilder sqlTile = new StringBuilder("");
	        appendSort(filter, sqlTile);
	        appendPaging(filter, sqlTile);
	        return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(CountryOriginFilter filter) {
		return executeCountQuery("");
	}
}
