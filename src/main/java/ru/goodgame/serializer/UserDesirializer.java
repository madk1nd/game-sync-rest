package ru.goodgame.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.val;
import org.springframework.boot.jackson.JsonComponent;
import ru.goodgame.dto.UserDTO;
import ru.goodgame.utils.Constants;

import javax.annotation.Nonnull;
import java.io.IOException;

@JsonComponent
public class UserDesirializer extends JsonDeserializer<UserDTO> {
    @Override
    public UserDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        @Nonnull JsonNode node = jsonParser.getCodec().readTree(jsonParser);

        @Nonnull val country = node.get(Constants.FIELD_COUNTRY).asText();
        @Nonnull val money = (Integer) node.get(Constants.FIELD_MONEY).numberValue();

        return new UserDTO(country, money);
    }
}
