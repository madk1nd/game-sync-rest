package ru.goodgame.model;

import javax.annotation.Nonnull;
import java.sql.SQLException;

public interface ActivityRepository {
    boolean saveActivity(@Nonnull String uuid, @Nonnull Integer activity) throws SQLException;
}
