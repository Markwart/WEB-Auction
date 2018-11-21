package com.itacademy.jd2.mm.auction.jdbc.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import com.itacademy.jd2.mm.auction.daoapi.IStepBlockDao;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IShippingMethod;
import com.itacademy.jd2.mm.auction.daoapi.entity.table.IStepBlock;
import com.itacademy.jd2.mm.auction.jdbc.impl.entity.StepBlock;
import com.itacademy.jd2.mm.auction.jdbc.impl.util.PreparedStatementAction;

@Repository
public class StepBlockDaoImpl extends AbstractDaoImpl<IStepBlock, Integer> implements IStepBlockDao {

	@Override
	public IStepBlock createEntity() {
		return new StepBlock();
	}

	@Override
	public void update(IStepBlock entity) {
		executeStatement(new PreparedStatementAction<IStepBlock>(
				String.format("update %s set name=?, step_1=?, step_2=?, step_3=?, step_4=?, step_5=?, updated=? where id=?", getTableName())) {
			@Override
			public IStepBlock doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getStep_1());
				pStmt.setInt(3, entity.getStep_2());
				pStmt.setInt(4, entity.getStep_3());
				pStmt.setInt(5, entity.getStep_4());
				pStmt.setInt(6, entity.getStep_5());
				pStmt.setObject(7, entity.getUpdated(), Types.TIMESTAMP);
				pStmt.setInt(8, entity.getId());

				pStmt.executeUpdate();
				return entity;
			}
		});
	}

	@Override
	public void insert(IStepBlock entity) {
		executeStatement(new PreparedStatementAction<IStepBlock>(
				String.format("insert into %s (name, step_1, step_2, step_3, step_4, step_5, created, updated) values(?,?,?,?,?,?,?,?)", getTableName()), true) {

			@Override
			public IStepBlock doWithPreparedStatement(final PreparedStatement pStmt) throws SQLException {
				pStmt.setString(1, entity.getName());
				pStmt.setInt(2, entity.getStep_1());
				pStmt.setInt(3, entity.getStep_2());
				pStmt.setInt(4, entity.getStep_3());
				pStmt.setInt(5, entity.getStep_4());
				pStmt.setInt(6, entity.getStep_5());
				pStmt.setObject(7, entity.getCreated(), Types.TIMESTAMP);
				pStmt.setObject(8, entity.getUpdated(), Types.TIMESTAMP);

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
		return "step_block";
	}

	@Override
	protected IStepBlock parseRow(final ResultSet resultSet) throws SQLException {
		final IStepBlock entity = createEntity();
		entity.setId((Integer) resultSet.getObject("id"));
		entity.setName(resultSet.getString("name"));
		entity.setStep_1(resultSet.getInt("step_1"));
		entity.setStep_2(resultSet.getInt("step_2"));
		entity.setStep_3(resultSet.getInt("step_3"));
		entity.setStep_4(resultSet.getInt("step_4"));
		entity.setStep_5(resultSet.getInt("step_5"));
		entity.setCreated(resultSet.getTimestamp("created"));
		entity.setUpdated(resultSet.getTimestamp("updated"));
		return entity;
	}
}
