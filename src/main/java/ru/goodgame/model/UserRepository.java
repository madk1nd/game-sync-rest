package ru.goodgame.model;

import ru.goodgame.dto.UserDTO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.SQLException;
import java.util.stream.Stream;

public interface UserRepository {
    boolean syncUserData(@Nonnull String uuid, @Nonnull UserDTO dto) throws SQLException;
    @Nullable UserDTO getUserData(@Nonnull String uuid) throws SQLException;
}
