package ru.goodgame.model;

import javax.annotation.Nonnull;
import java.util.stream.Stream;

public class SyncServiceImpl implements SyncService {

    @Nonnull private final UserRepository userRepository;

    public SyncServiceImpl(@Nonnull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Nonnull
    public Stream<String> getMoney() {
        return userRepository.getCountry();
    }
}
