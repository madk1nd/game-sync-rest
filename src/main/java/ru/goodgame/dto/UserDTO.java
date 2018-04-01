package ru.goodgame.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.EqualsAndHashCode;
import ru.goodgame.serializer.UserSerializer;

import javax.annotation.Nonnull;

@JsonSerialize(using = UserSerializer.class)
@EqualsAndHashCode
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
}
