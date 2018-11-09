package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IConditionDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.ICondition;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Condition;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class ConditionDaoImpl extends AbstractDaoImpl<ICondition, Integer> implements IConditionDao {

	@Override
	public ICondition createEntity() {
		return new Condition();
	}

	@Override
	public void insert(final ICondition entity) {
		executeStatement(new PreparedStatementAction<ICondition>(
				String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()), true) {
			@Override
			public ICondition doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
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
	public void update(final ICondition entity) {
		executeStatement(new PreparedStatementAction<ICondition>(
				String.format("update %s set name=?, updated=? where id=?", getTableName())) {
			@Override
			public ICondition doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	protected String getTableName() {
		return "condition";
	}

	@Override
	protected ICondition parseRow(final ResultSet resultSet) throws SQLException {
		final ICondition entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}
	
	/* @Override
	    public void save(ICondition... entities) {
	        try (Connection c = getConnection()) {
	            c.setAutoCommit(false);
	            try {

	                for (ICondition entity : entities) {
	                    PreparedStatement pStmt = c.prepareStatement(
	                            String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()),
	                            Statement.RETURN_GENERATED_KEYS);

	                    pStmt.setString(1, entity.getName());
	                    pStmt.setObject(2, entity.getCreated(), Types.TIMESTAMP);
	                    pStmt.setObject(3, entity.getUpdated(), Types.TIMESTAMP);

	                    pStmt.executeUpdate();

	                    final ResultSet rs = pStmt.getGeneratedKeys();
	                    rs.next();
	                    final int id = rs.getInt("id");

	                    rs.close();
	                    pStmt.close();
	                    entity.setId(id);
	                }

	                // the same should be done in 'update' DAO method
	                c.commit();
	            } catch (final Exception e) {
	                c.rollback();
	                throw new RuntimeException(e);
	            }

	        } catch (final SQLException e) {
	            throw new SQLExecutionException(e);
	        }
	    }*/

}
