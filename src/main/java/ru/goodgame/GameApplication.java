package ru.goodgame;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.goodgame.model.DatabaseConnection;
import ru.goodgame.model.MySQLConnection;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Nonnull;

@SpringBootApplication
@EnableSwagger2
public class GameApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameApplication.class, args);
    }

    @Value("${config.path:}")
    private String configFilePath;

    @Value("${screen.name:}")
    private String screenName;

    @Bean
    @Nonnull
    public DatabaseConnection getConnection() {
        return new MySQLConnection();
    }
    //     */
    //     * This path is automatically read either from application.properties, or from a command line
//    /**

//    @Bean
//    @Nonnull
//    public AuthConfig getConfiguration() {
//        return AuthConfig.fromFile(configFilePath, screenName);
//    }

//    @Bean
//	@Nonnull
//    public IUserRepository userRepository() {
//        return new UserRepository(cassandraConnector(), getConfiguration());
//    }
//
//    @Bean
//    @Nonnull
//    public IAgentDataRepository tokenDataRepository() {
//        return new AgentDataRepository(cassandraConnector(), getConfiguration());
//    }
//
//	@Bean
//	@Nonnull
//	public IAuthService agentEventsService() {
//        return new AuthService(userRepository(), tokenDataRepository());
//    }
//
//    @Bean
//    @Nonnull
//    public IConnectCassandra cassandraConnector() {
//        return CassandraConnector.getConnect(getConfiguration());
//    }
}
