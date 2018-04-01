package ru.goodgame.controller.mock;

import ru.goodgame.dto.ResponseDTO;
import ru.goodgame.dto.UserDTO;
import ru.goodgame.model.SyncService;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static ru.goodgame.dto.ResponseDTO.badResponse;
import static ru.goodgame.dto.ResponseDTO.goodResponse;

public class SyncServiceMock implements SyncService {

    private Map<String, UserDTO> users;
    private Map<String, List<Integer>> activity;

    public SyncServiceMock() {
        this.users = new HashMap<>();
        users.put("hash-1", new UserDTO("RU", 1000));
        users.put("hash-2", new UserDTO("UA", 2000));
        users.put("hash-3", new UserDTO("SP", 3000));
        users.put("hash-4", new UserDTO("KZ", 4000));
        users.put("hash-5", new UserDTO("BL", 5000));
        users.put("hash-6", new UserDTO("UZ", 6000));
        users.put("hash-7", new UserDTO("FR", 7000));
        users.put("hash-8", new UserDTO("GE", 8000));
        users.put("hash-9", new UserDTO("RU", 8000));
        users.put("hash-10", new UserDTO("UK", 10000));

        this.activity = new HashMap<>();
        activity.put("hash-1", Stream.of(1, 2, 3).collect(toList()));
        activity.put("hash-2", Stream.of(4, 5, 6).collect(toList()));

    }

    @Nonnull
    @Override
    public ResponseDTO saveUserData(@Nonnull String uuid, @Nonnull UserDTO dto) {
        if (users.containsKey(uuid)) {
            users.put(uuid, dto);
            return goodResponse("sync data was saved", "");
        } else {
            return goodResponse("sync data wasn't saved", "");
        }
    }

    @Nonnull
    @Override
    public ResponseDTO getUserInfo(@Nonnull String uuid) {
        if (users.containsKey(uuid)) {
            return goodResponse("sync data was saved", users.get(uuid));
        } else {
            return badResponse("Can't find data for user with id = " + uuid);
        }
    }

    @Nonnull
    @Override
    public ResponseDTO saveActivity(@Nonnull String uuid, @Nonnull Integer activity) {
        if (users.containsKey(uuid)) {
            List<Integer> list = this.activity.get("uuid");
            if (list != null) {
                list.add(activity);
            } else {
                this.activity.put(uuid, Stream.of(activity).collect(toList()));
            }
            return goodResponse("statistic data was saved", "");
        } else {
            return goodResponse("statistic data wasn't saved", "");
        }
    }
}
