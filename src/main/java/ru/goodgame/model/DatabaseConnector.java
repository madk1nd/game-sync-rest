package ru.goodgame.model;

import ru.goodgame.query.QueryParamsBinder;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseConnector {
      @Nonnull ResultSet executeQuery(@Nonnull String query, @Nonnull QueryParamsBinder binder) throws SQLException;
      boolean executeUpdate(@Nonnull String query, @Nonnull QueryParamsBinder binder) throws SQLException;
}
