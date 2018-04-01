package ru.goodgame.query;

import javax.annotation.Nonnull;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface QueryParamsBinder {
    void bindParams(@Nonnull PreparedStatement statement) throws SQLException;
}
