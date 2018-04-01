package ru.goodgame.model;

import lombok.extern.slf4j.Slf4j;
import ru.goodgame.dto.ResponseDTO;
import ru.goodgame.dto.UserDTO;

import javax.annotation.Nonnull;
import java.sql.SQLException;

import static ru.goodgame.dto.ResponseDTO.badResponse;
import static ru.goodgame.dto.ResponseDTO.goodResponse;

@Slf4j
public class SyncServiceImpl implements SyncService {

    @Nonnull private final UserRepository userRepository;
    @Nonnull private final ActivityRepository activityRepository;

    public SyncServiceImpl(@Nonnull UserRepository userRepository,
                           @Nonnull ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
    }

    @Nonnull
    @Override
    public ResponseDTO saveUserData(@Nonnull String uuid, @Nonnull UserDTO dto) {
        try {
            boolean result = userRepository.syncUserData(uuid, dto);
            return goodResponse(result ? "sync data was saved" : "sync data wasn't saved", "");
        } catch (SQLException e) {
            LOG.error("Can't update user data for uuid :: {}, cause :: {}", e.getMessage());
            return badResponse(e.getMessage());
        }
    }

    @Nonnull
    @Override
    public ResponseDTO getUserInfo(@Nonnull String uuid) {
        try {
            UserDTO userData = userRepository.getUserData(uuid);
            if (userData == null) {
                return badResponse(String.format("Can't find data for user with id = %s", uuid));
            }
            return goodResponse(String.format("Successfully found data for user with id = %s", uuid), userData);
        } catch (SQLException e) {
            return badResponse(e.getMessage());
        }
    }

    @Nonnull
    @Override
    public ResponseDTO saveActivity(@Nonnull String uuid, @Nonnull Integer activity) {
        try {
            boolean result = activityRepository.saveActivity(uuid, activity);
            return goodResponse(result ? "statistic data was saved" : "statistic data wasn't saved", "");
        } catch (SQLException e) {
            LOG.error("Can't save activity data :: {}, for uuid :: {} cause :: {}",
                    activity, uuid, e.getMessage());
            return badResponse(e.getMessage());
        }
    }
}
