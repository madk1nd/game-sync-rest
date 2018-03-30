package ru.goodgame;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.goodgame.model.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Nonnull;
import java.sql.SQLException;

@SpringBootApplication
@EnableScheduling
@EnableSwagger2
public class GameApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Value("${spring.datasource.url:}")
    private String url;

    @Value("${spring.datasource.username:}")
    private String username;

    @Value("${spring.datasource.password:}")
    private String password;

    @Bean
    @Nonnull
    public DatabaseConnector getConnection() throws SQLException {
        return MySQLConnector.getConnection(url, username, password);
    }

    @Bean
    @Nonnull
    public UserRepository getUserRepo() throws SQLException {
        return new UserRepositoryImpl(getConnection());
    }

    @Bean
    @Nonnull
    public SyncService getService() throws SQLException {
        return new SyncServiceImpl(getUserRepo());
    }
}
