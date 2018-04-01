package ru.goodgame.query;

import javax.annotation.Nonnull;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateUserQueryParams implements QueryParamsBinder {

    @Nonnull private final String uuid;
    @Nonnull private final Integer money;
    @Nonnull private final String country;

    public UpdateUserQueryParams(@Nonnull String uuid, @Nonnull Integer money, @Nonnull String country) {
        this.uuid = uuid;
        this.money = money;
        this.country = country;
    }

    @Override
    public void bindParams(@Nonnull PreparedStatement statement) throws SQLException {
        statement.setInt(1, money);
        statement.setString(2, country);
        statement.setString(3, uuid);
    }
}
