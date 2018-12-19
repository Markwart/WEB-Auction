package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IAuctionRuleDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IAuctionRule;
import com.itacademy.jd2.mm.auction.daoapi.filter.AuctionRuleFilter;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.AuctionRule;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class AuctionRuleDaoImpl extends AbstractDaoImpl<IAuctionRule, Integer> implements IAuctionRuleDao {

	@Override
	public IAuctionRule createEntity() {
		return new AuctionRule();
	}

	@Override
	public void update(IAuctionRule entity) {
		executeStatement(new PreparedStatementAction<IAuctionRule>(
				String.format("update %s set index=?, theme=?, text=?, updated=? where id=?", getTableName())) {
			@Override
			public IAuctionRule doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getIndex());
				pStmt.setString(2, entity.getTheme());
				pStmt.setString(3, entity.getText());
				pStmt.setObject(4, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(5, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IAuctionRule entity) {
		executeStatement(new PreparedStatementAction<IAuctionRule>(String.format(
				"insert into %s (index, theme, text, created, updated) values(?,?,?,?,?)", getTableName()), true) {

			@Override
			public IAuctionRule doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getIndex());
				pStmt.setString(2, entity.getTheme());
				pStmt.setString(3, entity.getText());
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
		return "auction_rule";
	}

	@Override
	protected IAuctionRule parseRow(final ResultSet resultSet) throws SQLException {
		final IAuctionRule entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setIndex(resultSet.getString("index"));
		entity.setTheme(resultSet.getString("theme"));
		entity.setText(resultSet.getString("text"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}

	@Override
	public List<IAuctionRule> find(AuctionRuleFilter filter) {
		final StringBuilder sqlTile = new StringBuilder("");
		appendSort(filter, sqlTile);
		appendPaging(filter, sqlTile);
		return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(AuctionRuleFilter filter) {
		return executeCountQuery("");
	}
}
