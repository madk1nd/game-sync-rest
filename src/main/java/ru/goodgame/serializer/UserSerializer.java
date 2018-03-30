package ru.goodgame.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import ru.goodgame.dto.UserDTO;
import ru.goodgame.utils.Constants;

import java.io.IOException;

@JsonComponent
public class UserSerializer extends JsonSerializer<UserDTO> {
    @Override
    public void serialize(UserDTO userDTO, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField(Constants.FIELD_MONEY, userDTO.getMoney());
        jsonGenerator.writeStringField(Constants.FIELD_COUNTRY, userDTO.getCountry());
        jsonGenerator.writeEndObject();
    }
}
