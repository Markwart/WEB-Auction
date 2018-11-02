package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import com.itacademy.jd2.mm.auction.daoapi.ICompositionDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IComposition;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Composition;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.SQLExecutionException;

public class CompositionDaoImpl extends AbstractDaoImpl<IComposition, Integer> implements ICompositionDao {

	@Override
	public IComposition createEntity() {
		return new Composition();
	}

	@Override
	public void insert(final IComposition entity) {
		executeStatement(new PreparedStatementAction<IComposition>(
				String.format("insert into %s (name, created, updated) values(?,?,?)", getTableName()), true) {
			
			@Override
			public IComposition doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
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
	public void update(IComposition entity) {
		executeStatement(new PreparedStatementAction<IComposition>(
				String.format("update %s set name=?, updated=? where id=?", getTableName())) {
			@Override
			public IComposition doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
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
		return "composition";
	}
	
	@Override
	protected IComposition parseRow(final ResultSet resultSet) throws SQLException {
		final IComposition entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public void save(IComposition... entities) {
		 try (Connection c = getConnection()) {
	            c.setAutoCommit(false);
	            try {

	                for (IComposition entity : entities) {
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
	}

}
