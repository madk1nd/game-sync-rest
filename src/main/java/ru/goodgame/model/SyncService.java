package ru.goodgame.model;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public interface SyncService {
      @Nonnull Stream<String> getMoney();
}
