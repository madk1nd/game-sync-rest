package ru.goodgame.model;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseConnector {
      @Nonnull ResultSet executeQuery(@Nonnull String query, @Nonnull Object... params) throws SQLException;
      boolean executeUpdate(@Nonnull String query, @Nonnull Object... params) throws SQLException;
}
