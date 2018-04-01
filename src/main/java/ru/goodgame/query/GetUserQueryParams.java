package ru.goodgame.query;

import javax.annotation.Nonnull;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GetUserQueryParams implements QueryParamsBinder {

    @Nonnull private final String uuid;

    public GetUserQueryParams(@Nonnull String uuid) {
        this.uuid = uuid;
    }

    @Override
    public void bindParams(@Nonnull PreparedStatement statement) throws SQLException {
        statement.setString(1, uuid);
    }
}
