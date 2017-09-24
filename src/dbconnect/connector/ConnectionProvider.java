package dbconnect.connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dbconnect.properties.Constants;

public enum ConnectionProvider implements AutoCloseable {
	INSTANCE;
	public static ConnectionProvider getInstance() {
		return INSTANCE;
	}

	private static DataSource dataSource = null;
	private static Connection connection;
	private static final String datasourceName = Constants.FilePath.DATASOURCE_NAME;

	private synchronized static void init() throws NamingException {
		InitialContext intitCtx = new InitialContext();
		ConnectionProvider.dataSource = (DataSource) intitCtx.lookup(ConnectionProvider.datasourceName);
	}

	public synchronized static Connection getConnection() throws SQLException, NamingException {
		init();
		try (Connection connection = ConnectionProvider.connection) {
			if (ConnectionProvider.connection == null || ConnectionProvider.connection.isClosed())
				ConnectionProvider.connection = ConnectionProvider.dataSource.getConnection();
		}
		return connection;
	}

	public PreparedStatement getPreparedStatement(String sql) throws Exception {
		return getConnection().prepareStatement(sql);
	}

	public void commit() throws SQLException {
		try (Connection connection = ConnectionProvider.connection) {
			ConnectionProvider.connection.commit();
		}
	}

	public void rollback() throws SQLException {
		try (Connection connection = ConnectionProvider.connection) {
			ConnectionProvider.connection.rollback();
		}
	}

	@Override
	public void close() throws SQLException {
		try (Connection connection = ConnectionProvider.connection) {
			ConnectionProvider.connection.close();
		}
	}
}