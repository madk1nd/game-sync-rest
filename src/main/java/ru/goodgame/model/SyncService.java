package ru.goodgame.model;

import ru.goodgame.dto.ResponseDTO;
import ru.goodgame.dto.UserDTO;

import javax.annotation.Nonnull;

public interface SyncService {
      @Nonnull ResponseDTO saveUserData(@Nonnull String uuid, @Nonnull UserDTO dto);
      @Nonnull ResponseDTO getUserInfo(@Nonnull String uuid);
      @Nonnull ResponseDTO saveActivity(@Nonnull String uuid, @Nonnull Integer activity);
}
