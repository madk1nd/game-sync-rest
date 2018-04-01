package ru.goodgame.model;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ru.goodgame.query.ActivityQueryParams;

import javax.annotation.Nonnull;
import java.sql.SQLException;

@Slf4j
public class ActivityRepositoryImpl implements ActivityRepository {

    @Nonnull private static final String QUERY = "INSERT INTO `activity` " +
            "(`user_uuid`,`activity`, `time`) VALUES (ordered_uuid(?), ?, NOW());";

    @Nonnull private final DatabaseConnector connector;

    public ActivityRepositoryImpl(@Nonnull DatabaseConnector connector) {
        this.connector = connector;
    }

    @Override
    public boolean saveActivity(@Nonnull String uuid, @Nonnull Integer activity) throws SQLException {
        @Nonnull val params = new ActivityQueryParams(uuid, activity);
        return connector.executeUpdate(QUERY, params);
    }
}
