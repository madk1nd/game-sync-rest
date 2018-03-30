package ru.goodgame.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import ru.goodgame.serializer.UserSerializer;

import javax.annotation.Nonnull;

@JsonSerialize(using = UserSerializer.class)
public class UserDTO {
    @Nonnull private final String country;
    @Nonnull private final Integer money;

    public UserDTO(@Nonnull String country, @Nonnull Integer money) {
        this.country = country;
        this.money = money;
    }

    @Nonnull
    public String getCountry() {
        return country;
    }

    @Nonnull
    public Integer getMoney() {
        return money;
    }

    @Nonnull
    public static UserDTO empty() {
        return new UserDTO("", 0);
    }

    public boolean isEmpty(UserDTO dto) {
        return "".equals(dto.getCountry()) && money == 0;
    }
}
