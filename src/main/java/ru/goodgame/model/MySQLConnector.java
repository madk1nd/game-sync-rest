package ru.goodgame.model;


import lombok.val;

import javax.annotation.Nonnull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLConnector implements DatabaseConnector {

    @Nonnull private final Connection connection;

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
    public ResultSet executeQuery(@Nonnull String query) throws SQLException {
        @Nonnull val statement = connection.createStatement();
        statement.executeQuery(query);
        return statement.getResultSet();
    }
}
