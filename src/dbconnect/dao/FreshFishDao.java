package dbconnect.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dbconnect.connector.ConnectionProvider;
import dbconnect.domain.FreshFish;
import dbconnect.domain.FreshFishes;
import dbconnect.properties.Queries;

public class FreshFishDao {
	private static final String SQL_FIND_BY_FISH_CODE_OR_FISH_NAME
			= "SELECT * FROM " + Queries.TableName.FRESH_FISH
					+ " WHERE " + Queries.ColumnName.FISH_CODE + " = ? "
					+ " OR " + Queries.ColumnName.FISH_NAME + " = ? "
					+ " ORDER BY " + Queries.ColumnName.FRESH_FISH_ID + ";";

	public FreshFishes findByFishCodeOrFishName(String targetFishName, int targetFishCode)
			throws SQLException, Exception {
		List<FreshFish> fishRecords = new ArrayList<>();

		try (ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
				PreparedStatement statement = connectionProvider
						.getPreparedStatement(SQL_FIND_BY_FISH_CODE_OR_FISH_NAME);) {

			statement.setInt(1, targetFishCode);
			statement.setString(2, targetFishName);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {

					int freshFishId = resultSet.getInt(Queries.ColumnName.FRESH_FISH_ID);
					int fishCode = resultSet.getInt(Queries.ColumnName.FISH_CODE);
					String fishName = resultSet.getString(Queries.ColumnName.FISH_NAME);
					String district = resultSet.getString(Queries.ColumnName.DISTRICT);
					int unitPrice = resultSet.getInt(Queries.ColumnName.UNIT_PRICE);

					FreshFish fish = new FreshFish.Builder().freshFishID(freshFishId).fishCode(fishCode)
							.fishName(fishName).district(district).unitPrice(unitPrice).build();

					fishRecords.add(fish);
				}
			}
		}

		fishRecords = Collections.unmodifiableList(fishRecords);
		return new FreshFishes(fishRecords);
	}
}
