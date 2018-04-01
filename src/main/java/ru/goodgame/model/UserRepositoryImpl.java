package ru.goodgame.model;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ru.goodgame.dto.UserDTO;
import ru.goodgame.query.GetUserQueryParams;
import ru.goodgame.query.UpdateUserQueryParams;
import ru.goodgame.utils.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.Null;
import java.sql.ResultSet;
import java.sql.SQLException;

import static ru.goodgame.utils.Constants.FIELD_COUNTRY;
import static ru.goodgame.utils.Constants.FIELD_MONEY;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Nonnull private static final String GET_USER_DATA_QUERY = "SELECT " +
            "`money`,`country` " +
            "FROM users WHERE `uuid`=ordered_uuid(?) ;";

    @Nonnull private static final String SAVE_USER_DATA_QUERY = "UPDATE `users` " +
            "SET `money` = ?, `country` = ?, `time` = NOW() " +
            "WHERE uuid = ordered_uuid(?);";

    @Nonnull private final DatabaseConnector connector;

    public UserRepositoryImpl(@Nonnull DatabaseConnector connector) {
        this.connector = connector;
    }

    @Override
    public boolean syncUserData(@Nonnull String uuid, @Nonnull UserDTO dto) throws SQLException {
        @Nonnull val params = new UpdateUserQueryParams(uuid, dto.getMoney(), dto.getCountry());
        return connector.executeUpdate(SAVE_USER_DATA_QUERY, params);
    }

    @Nullable
    @Override
    public UserDTO getUserData(@Nonnull String uuid) throws SQLException {
        @Nonnull val params = new GetUserQueryParams(uuid);
        @Nonnull ResultSet resultSet = connector.executeQuery(GET_USER_DATA_QUERY, params);

        if (resultSet.next()) {
            @Nonnull val country = resultSet.getString(FIELD_COUNTRY);
            int money = resultSet.getInt(FIELD_MONEY);
            return new UserDTO(country, money);
        } else {
            return null;
        }
    }
}
