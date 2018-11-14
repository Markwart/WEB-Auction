package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IDeferredBidDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IDeferredBid;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.DeferredBid;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Item;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class DereffedBidDaoImpl extends AbstractDaoImpl<IDeferredBid, Integer> implements IDeferredBidDao{

	@Override
	public IDeferredBid createEntity() {
		return new DeferredBid();
	}

	@Override
	public void update(IDeferredBid entity) {
		executeStatement(new PreparedStatementAction<IDeferredBid>(
				String.format("update %s set item_id=?, price_bid=?, user_bid_id=?, status_bid=?, updated=? where id=?", getTableName())) {
			@Override
			public IDeferredBid doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getItem().getId());
				pStmt.setBigDecimal(2, entity.getPriceBid());
				pStmt.setInt(3, entity.getUserAccount().getId());
				pStmt.setString(4, entity.getStatusBid());
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(6, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});	
	}

	@Override
	public void insert(IDeferredBid entity) {
		executeStatement(new PreparedStatementAction<IDeferredBid>(
				String.format("insert into %s (item_id, price_bid, user_bid_id, status_bid, created, updated) values(?,?,?,?,?,?)",
						getTableName()),
				true) {

			@Override
			public IDeferredBid doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getItem().getId());
				pStmt.setBigDecimal(2, entity.getPriceBid());
				pStmt.setInt(3, entity.getUserAccount().getId());
				pStmt.setString(4, entity.getStatusBid());
				pStmt.setObject(5, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(6, entity.getUpdated(), Types.TIMESTAMP);

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
		return "deferred_bid";
	}
	
	@Override
	protected IDeferredBid parseRow(final ResultSet resultSet) throws SQLException {
		final IDeferredBid entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setPriceBid(resultSet.getBigDecimal("price_bid"));
		entity.setStatusBid(resultSet.getString("status_bid"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		
		final Item item = new Item();
		item.setId((Integer) resultSet.getObject("item_id"));
		entity.setItem(item);

		final UserAccount userAccount = new UserAccount();
		userAccount.setId((Integer) resultSet.getObject("user_bid_id"));
		entity.setUserAccount(userAccount);

		return entity;
	}
}
