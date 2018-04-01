package ru.goodgame.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.goodgame.controller.mock.SyncServiceMock;
import ru.goodgame.controller.mock.UserJsonModule;

@Configuration
public class SyncApplicationTestConfig {
    @Bean
    public SyncServiceMock getMock() {
        return new SyncServiceMock();
    }

    @Bean
    public UserJsonModule getModule() {
        return new UserJsonModule();
    }
}
