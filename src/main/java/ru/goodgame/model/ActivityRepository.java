package ru.goodgame.model;

import javax.annotation.Nonnull;

public interface ActivityRepository {
    boolean saveActivity(@Nonnull String uuid, @Nonnull Integer activity);
}
