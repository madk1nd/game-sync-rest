package ru.goodgame.model;

import ru.goodgame.dto.UserDTO;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public interface UserRepository {
    boolean save(@Nonnull String uuid, @Nonnull UserDTO dto);
    @Nonnull UserDTO getUser(@Nonnull String uuid);
}
