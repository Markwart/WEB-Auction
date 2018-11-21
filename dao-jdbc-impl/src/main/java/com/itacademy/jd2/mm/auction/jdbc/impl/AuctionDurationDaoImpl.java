package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IAuctionDurationDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionDuration;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.AuctionDuration;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class AuctionDurationDaoImpl extends AbstractDaoImpl<IAuctionDuration, Integer> implements IAuctionDurationDao{

	@Override
	public IAuctionDuration createEntity() {
		return new AuctionDuration();
	}

	@Override
	public void update(IAuctionDuration entity) {
		executeStatement(new PreparedStatementAction<IAuctionDuration>(
				String.format("update %s set min=?, updated=? where id=?", getTableName())) {
			@Override
			public IAuctionDuration doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getMin());
				pStmt.setObject(2, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(3, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});		
	}

	@Override
	public void insert(IAuctionDuration entity) {
		executeStatement(new PreparedStatementAction<IAuctionDuration>(
				String.format("insert into %s (min, created, updated) values(?,?,?)", getTableName()), true) {

			@Override
			public IAuctionDuration doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getMin());
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
		return "auction_duration";
	}
	
	@Override
	protected IAuctionDuration parseRow(final ResultSet resultSet) throws SQLException {
		final IAuctionDuration entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setMin(resultSet.getInt("min"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

}
