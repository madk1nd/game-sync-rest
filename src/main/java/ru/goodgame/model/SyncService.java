package ru.goodgame.model;

import ru.goodgame.dto.ResponseDTO;
import ru.goodgame.dto.UserDTO;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.stream.Stream;

public interface SyncService {
      @Nonnull ResponseDTO saveUserData(@Nonnull String uuid, @Nonnull UserDTO dto);
      @Nonnull UserDTO getUserInfo(@Nonnull String uuid);
      @Nonnull ResponseDTO saveActivity(@Nonnull String uuid, @Nonnull Integer activity);
}
