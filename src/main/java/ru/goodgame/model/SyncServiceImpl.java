package ru.goodgame.model;

import lombok.extern.slf4j.Slf4j;
import ru.goodgame.dto.ResponseDTO;
import ru.goodgame.dto.Result;
import ru.goodgame.dto.UserDTO;

import javax.annotation.Nonnull;

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
        if (userRepository.save(uuid, dto))
            return new ResponseDTO(
                    Result.OK,
                    "Successfully saved user with id = " + uuid
            );
        else
            return new ResponseDTO(
                    Result.FAIL,
                    "Can't save user with id = " + uuid
            );
    }

    @Nonnull
    @Override
    public UserDTO getUserInfo(@Nonnull String uuid) {
        return userRepository.getUser(uuid);
    }

    @Nonnull
    @Override
    public ResponseDTO saveActivity(@Nonnull String uuid, @Nonnull Integer activity) {
        if (activityRepository.saveActivity(uuid, activity))
            return new ResponseDTO(
                    Result.OK,
                    "Successfully saved user activity for id = " + uuid
            );
        else
            return new ResponseDTO(
                    Result.FAIL,
                    "Can't save user activity with id = " + uuid
            );
    }
}
