package ru.goodgame.controller.mock;

import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Service;
import ru.goodgame.dto.UserDTO;
import ru.goodgame.serializer.UserDesirializer;
import ru.goodgame.serializer.UserSerializer;

public class UserJsonModule extends SimpleModule {
    public UserJsonModule() {
        this.addDeserializer(UserDTO.class, new UserDesirializer());
        this.addSerializer(UserDTO.class, new UserSerializer());
    }
}
