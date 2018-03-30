package ru.goodgame.model;

import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface DatabaseConnector {
      @Nonnull ResultSet executeQuery(@Nonnull String query) throws SQLException;
}
