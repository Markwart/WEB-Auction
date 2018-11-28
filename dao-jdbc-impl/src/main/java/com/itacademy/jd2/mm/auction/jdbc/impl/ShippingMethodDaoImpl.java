package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IShippingMethodDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.filter.ShippingMethodFilter;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.ShippingMethod;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class ShippingMethodDaoImpl extends AbstractDaoImpl<IShippingMethod, Integer> implements IShippingMethodDao {

	@Override
	public IShippingMethod createEntity() {
		return new ShippingMethod();
	}

	@Override
	public void update(IShippingMethod entity) {
		executeStatement(new PreparedStatementAction<IShippingMethod>(
				String.format("update %s set name=?, cost=?, delivery_time=?, updated=? where id=?", getTableName())) {
			@Override
			public IShippingMethod doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setBigDecimal(2, entity.getCost());
				pStmt.setString(3, entity.getDeliveryTime());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IShippingMethod entity) {
		executeStatement(new PreparedStatementAction<IShippingMethod>(
				String.format("insert into %s (name, cost, delivery_time, created, updated) values(?,?,?,?,?)",
						getTableName()),
				true) {

			@Override
			public IShippingMethod doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setBigDecimal(2, entity.getCost());
				pStmt.setString(3, entity.getDeliveryTime());
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
		return "shipping_method";
	}

	@Override
	protected IShippingMethod parseRow(final ResultSet resultSet) throws SQLException {
		final IShippingMethod entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCost(resultSet.getBigDecimal("cost"));
		entity.setDeliveryTime(resultSet.getString("delivery_time"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public List<IShippingMethod> find(ShippingMethodFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(ShippingMethodFilter filter) {
		return executeCountQuery("");
	}
}
