package ru.goodgame.model;


import lombok.val;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.*;
import java.util.concurrent.ConcurrentHashMap;

public class MySQLConnector implements DatabaseConnector {

    @Nonnull private final Connection connection;
    @Nonnull private static final ConcurrentHashMap<String, PreparedStatement> statements = new ConcurrentHashMap<>();

    public MySQLConnector(@Nonnull Connection connection) {
        this.connection = connection;
    }

    @Nonnull
    public static DatabaseConnector getConnection(@Nonnull String url,
                                                  @Nonnull String username,
                                                  @Nonnull String password) throws SQLException {
        @Nonnull val connection = DriverManager.getConnection(url, username, password);
        @Nonnull val sqlConnection = new MySQLConnector(connection);
        return sqlConnection;
    }

    @Override
    @Nonnull
    public ResultSet executeQuery(@Nonnull String query, @Nonnull Object... params) throws SQLException {
        @Nonnull PreparedStatement preparedStatement = bindParams(query, params);
        return preparedStatement.executeQuery();
    }

    @Override
    public boolean executeUpdate(@Nonnull String query, @Nonnull Object... params) throws SQLException {
        @Nonnull PreparedStatement preparedStatement = bindParams(query, params);
        return preparedStatement.executeUpdate() > 0;
    }

    @Nonnull
    private PreparedStatement bindParams(@Nonnull String query, @Nonnull Object... params) throws SQLException {
        @Nonnull PreparedStatement preparedStatement = getStatement(query);

        int count = 1;
        for (Object obj : params) {
            if (obj instanceof Integer) preparedStatement.setInt(count++, (Integer) obj);
            if (obj instanceof String) preparedStatement.setString(count++, (String) obj);
            if (obj instanceof Timestamp) preparedStatement.setTimestamp(count++, (Timestamp) obj);
        }

        return preparedStatement;
    }

    @Nonnull
    private PreparedStatement getStatement(@Nonnull String query) throws SQLException{
        @Nullable PreparedStatement preparedStatement = statements.get(query);
        if (preparedStatement == null) {
            preparedStatement = connection.prepareStatement(query);
            statements.put(query, preparedStatement);
        }
        return preparedStatement;
    }


}
