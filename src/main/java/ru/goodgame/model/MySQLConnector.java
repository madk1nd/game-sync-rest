package ru.goodgame.model;


import lombok.val;
import ru.goodgame.query.QueryParamsBinder;

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
    public ResultSet executeQuery(@Nonnull String query, @Nonnull QueryParamsBinder binder) throws SQLException {
        @Nonnull PreparedStatement preparedStatement = getStatement(query);
        binder.bindParams(preparedStatement);
        return preparedStatement.executeQuery();
    }

    @Override
    public boolean executeUpdate(@Nonnull String query, @Nonnull QueryParamsBinder binder) throws SQLException {
        @Nonnull PreparedStatement preparedStatement = getStatement(query);
        binder.bindParams(preparedStatement);
        return preparedStatement.executeUpdate() > 0;
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
