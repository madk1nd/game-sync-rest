package ru.goodgame.model;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public interface UserRepository {
    @Nonnull Stream<String> getCountry();
}
