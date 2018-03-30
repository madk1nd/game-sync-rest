package ru.goodgame.model;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import ru.goodgame.dto.UserDTO;
import ru.goodgame.utils.Constants;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Nonnull private final DatabaseConnector connector;

    public UserRepositoryImpl(@Nonnull DatabaseConnector connector) {
        this.connector = connector;
    }

    @Override
    public boolean save(@Nonnull String uuid, @Nonnull UserDTO dto) {
        @Nonnull val query = "INSERT INTO `users` " +
                "(`uuid`,`money`,`country`,`registration_time`) " +
                "VALUES (?, ?, ?, ?);";
        try {
            Object[] params = new Object[] { uuid, dto.getMoney(), dto.getCountry(), new Timestamp(new Date().getTime()) };
            return connector.executeUpdate(query, params);
        } catch (SQLException e) {
            LOG.error("Exception during executing update query :: {}, cause :: {}",
                    query, e.getMessage());
            return false;
        }
    }

    @Nonnull
    @Override
    public UserDTO getUser(@Nonnull String uuid) {
        @Nonnull val query = "SELECT " +
                "`money`,`country` " +
                "FROM users WHERE `uuid`=? ;";
        ResultSet resultSet;
        try {
            Object[] params = new Object[] { uuid };
            resultSet = connector.executeQuery(query, params);

            if (resultSet.next()) {
                String country = resultSet.getString(Constants.FIELD_COUNTRY);
                int money = resultSet.getInt(Constants.FIELD_MONEY);
                return new UserDTO(country, money);
            } else {
                LOG.error("Can't find user with id = {}", uuid);
                return UserDTO.empty();
            }
        } catch (SQLException e) {
            LOG.error("Exception during executing update query :: {}, cause :: {}",
                    query, e.getMessage());
            return UserDTO.empty();
        }
    }
}
