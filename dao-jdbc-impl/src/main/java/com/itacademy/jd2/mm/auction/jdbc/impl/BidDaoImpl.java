package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IBidDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.enums.StatusBid;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IBid;
import com.itacademy.jd2.mm.auction.daoapi.filter.BidFilter;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Bid;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Item;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class BidDaoImpl extends AbstractDaoImpl<IBid, Integer> implements IBidDao {

	@Override
	public IBid createEntity() {
		return new Bid();
	}

	@Override
	public void update(IBid entity) {
		executeStatement(new PreparedStatementAction<IBid>(
				String.format("update %s set item_id=?, price_bid=?, user_bid_id=?, status_bid=?, updated=? where id=?",
						getTableName())) {
			@Override
			public IBid doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getItem().getId());
				pStmt.setBigDecimal(2, entity.getPriceBid());
				pStmt.setInt(3, entity.getUserBid().getId());
				pStmt.setObject(4, entity.getStatusBid());
				pStmt.setObject(5, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(6, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IBid entity) {
		executeStatement(new PreparedStatementAction<IBid>(String.format(
				"insert into %s (item_id, price_bid, user_bid_id, status_bid, created, updated) values(?,?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IBid doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setInt(1, entity.getItem().getId());
				pStmt.setBigDecimal(2, entity.getPriceBid());
				pStmt.setInt(3, entity.getUserBid().getId());
				pStmt.setObject(4, entity.getStatusBid());
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
		return "bid";
	}

	@Override
	protected IBid parseRow(final ResultSet resultSet, final Set<String> columns) throws SQLException {
		final IBid entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setPriceBid(resultSet.getBigDecimal("price_bid"));
		entity.setStatusBid((StatusBid) resultSet.getObject("status_bid"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final Integer itemId = (Integer) resultSet.getObject("item_id");
		if (itemId != null) {
			final Item item = new Item();
			item.setId(itemId);
			if (columns.contains("item_name")) {
				item.setName(resultSet.getString("item_name"));
			}
			entity.setItem(item);
		}

		final Integer userAccountId = (Integer) resultSet.getObject("user_bid_id");
		if (userAccountId != null) {
			final UserAccount userAccount = new UserAccount();
			userAccount.setId(userAccountId);
			if (columns.contains("user_email")) {
				userAccount.setEmail(resultSet.getString("user_email"));
			}
			entity.setUserBid(userAccount);
		}
		return entity;
	}

	@Override
	public List<IBid> find(BidFilter filter) {
		final StringBuilder sqlTile;
		if (filter.getFetchUserAccount() & filter.getFetchItem()) {
			sqlTile = new StringBuilder(
					String.format("select bid.*, user_account.email as user_email, item.name as item_name from %s", getTableName()));
		} else {
			sqlTile = new StringBuilder(String.format("bid.* from %s", getTableName()));
		}

		appendJOINs(sqlTile, filter);
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQueryWithCustomSelect(sqlTile.toString());
	}

	/*
	 * "select bid.*, item.name as item_name from %s"
	 * append(" join item on (item.id=bid.item_id) ")
	 */

	private void appendJOINs(final StringBuilder sb, final BidFilter filter) {
		if (filter.getFetchUserAccount() & filter.getFetchItem()) {
			sb.append(" join user_account on (user_account.id=bid.user_bid_id) ")
					.append(" join item on (item.id=bid.item_id) ");
		}
	}

	@Override
	public long getCount(BidFilter filter) {
		return executeCountQuery("");
	}

	@Override
	public IBid getFullInfo(Integer id) {
		throw new RuntimeException("not implemneted");
	}

	@Override
	public List<IBid> findRelatedBidsByItem(Integer id) {
		throw new RuntimeException("not implemented");
	}

	@Override
	public List<IBid> findRelatedBidsByUser(Integer id) {
		throw new RuntimeException("not implemented");
	}
}
