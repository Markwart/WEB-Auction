package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IItemDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IItem;
import com.itacademy.jd2.mm.auction.daoapi.filter.ItemFilter;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Category;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Composition;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Condition;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.CountryOrigin;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.Item;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.UserAccount;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class ItemDaoImpl extends AbstractDaoImpl<IItem, Integer> implements IItemDao {

	@Override
	public IItem createEntity() {
		return new Item();
	}

	@Override
	public void update(IItem entity) {
		executeStatement(new PreparedStatementAction<IItem>(String.format(
				"update %s set name=?, auction_end=?, starting_price=?, category_id=?, year=?, country_origin_id=?, condition_id=?, composition_id=?, image=?, text=?, seller_id=?, status_auction=?, updated=? where id=?",
				getTableName())) {
			@Override
			public IItem doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getAuctionEnd(), Types.TIMESTAMP);
				pStmt.setBigDecimal(3, entity.getStartingPrice());
				pStmt.setInt(4, entity.getCategory().getId());
				pStmt.setInt(5, entity.getYear());
				pStmt.setInt(6, entity.getCountryOrigin().getId());
				pStmt.setInt(7, entity.getCondition().getId());
				pStmt.setInt(8, entity.getComposition().getId());
				pStmt.setString(9, entity.getImage());
				pStmt.setString(10, entity.getText());
				pStmt.setInt(11, entity.getSeller().getId());
				pStmt.setString(12, entity.getStatusAuction());
				pStmt.setObject(13, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(14, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IItem entity) {
		executeStatement(new PreparedStatementAction<IItem>(String.format(
				"insert into %s (name, auction_end, starting_price, category_id, year, country_origin_id, condition_id, composition_id, image, text, seller_id, status_auction, created, updated) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
				getTableName()), true) {

			@Override
			public IItem doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setObject(2, entity.getAuctionEnd(), Types.TIMESTAMP);
				pStmt.setBigDecimal(3, entity.getStartingPrice());
				pStmt.setInt(4, entity.getCategory().getId());
				pStmt.setInt(5, entity.getYear());
				pStmt.setInt(6, entity.getCountryOrigin().getId());
				pStmt.setInt(7, entity.getCondition().getId());
				pStmt.setInt(8, entity.getComposition().getId());
				pStmt.setString(9, entity.getImage());
				pStmt.setString(10, entity.getText());
				pStmt.setInt(11, entity.getSeller().getId());
				pStmt.setString(12, entity.getStatusAuction());
				pStmt.setObject(13, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(14, entity.getUpdated(), Types.TIMESTAMP);

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
		return "item";
	}

	@Override
	protected IItem parseRow(final ResultSet resultSet) throws SQLException {
		final IItem entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setAuctionEnd(resultSet.getTimestamp("auction_end"));
		entity.setStartingPrice(resultSet.getBigDecimal("starting_price"));
		entity.setYear(resultSet.getInt("year"));
		entity.setImage(resultSet.getString("image"));
		entity.setText(resultSet.getString("text"));
		entity.setStatusAuction(resultSet.getString("status_auction"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));

		final UserAccount seller = new UserAccount();
		seller.setId((Integer) resultSet.getObject("seller_id"));
		entity.setSeller(seller);

		final Category category = new Category();
		category.setId((Integer) resultSet.getObject("category_id"));
		entity.setCategory(category);

		final CountryOrigin countryOrigin = new CountryOrigin();
		countryOrigin.setId((Integer) resultSet.getObject("country_origin_id"));
		entity.setCountryOrigin(countryOrigin);
		
		final Condition condition = new Condition();
		condition.setId((Integer) resultSet.getObject("condition_id"));
		entity.setCondition(condition);
		
		final Composition composition = new Composition();
		composition.setId((Integer) resultSet.getObject("composition_id"));
		entity.setComposition(composition);
		
		return entity;
	}

	@Override
	public List<IItem> find(ItemFilter filter) {
		 final StringBuilder sqlTile = new StringBuilder("");
	        appendSort(filter, sqlTile);
	        appendPaging(filter, sqlTile);
	        return executeFindQuery(sqlTile.toString());
	}

	@Override
	public long getCount(ItemFilter filter) {
		return executeCountQuery("");
	}
}
