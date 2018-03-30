package ru.goodgame.model;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.annotation.Nonnull;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

@Slf4j
public class ActivityRepositoryImpl implements ActivityRepository {

    @Nonnull private final DatabaseConnector connector;

    public ActivityRepositoryImpl(@Nonnull DatabaseConnector connector) {
        this.connector = connector;
    }

    @Override
    public boolean saveActivity(@Nonnull String uuid, @Nonnull Integer activity) {
        @Nonnull val query = "INSERT INTO `activity` " +
                "(`user_uuid`,`activity`,`time`) VALUES (?, ?, ?);";
        try {
            Object[] params = new Object[] { uuid, activity, new Timestamp(new Date().getTime()) };
            return connector.executeUpdate(query, params);
        } catch (SQLException e) {
            LOG.error("Exception during executing update query :: {}, cause :: {}",
                    query, e.getMessage());
            return false;
        }
    }
}
