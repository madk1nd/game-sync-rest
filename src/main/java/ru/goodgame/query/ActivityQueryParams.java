package ru.goodgame.query;

import javax.annotation.Nonnull;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActivityQueryParams implements QueryParamsBinder {

    @Nonnull private final String uuid;
    @Nonnull private final Integer activity;

    public ActivityQueryParams(@Nonnull String uuid, @Nonnull Integer activity) {
        this.uuid = uuid;
        this.activity = activity;
    }

    @Override
    public void bindParams(@Nonnull PreparedStatement statement) throws SQLException {
        statement.setString(1, uuid);
        statement.setInt(2, activity);
    }
}
