package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.itacademy.jd2.mm.auction.daoapi.ICategoryDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICategory;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Category;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

public class CategoryDaoImpl extends AbstractDaoImpl<ICategory, Integer> implements ICategoryDao {

	@Override
	public ICategory createEntity() {
		return new Category();
	}

	@Override
	public void update(ICategory entity) {
		executeStatement(new PreparedStatementAction<ICategory>(
				String.format("update %s set name=?, updated=? where id=?", getTableName())) {
			@Override
			public ICategory doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(final ICategory entity) {
		executeStatement(new PreparedStatementAction<ICategory>(
				String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()), true) {

			@Override
			public ICategory doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
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
		return "category";
	}
	
	@Override
	protected ICategory parseRow(final ResultSet resultSet) throws SQLException {
		final ICategory entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}
}
