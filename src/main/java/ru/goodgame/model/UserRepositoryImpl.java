package ru.goodgame.model;

import lombok.val;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UserRepositoryImpl implements UserRepository {

    @Nonnull private final DatabaseConnector connector;

    public UserRepositoryImpl(@Nonnull DatabaseConnector connector) {
        this.connector = connector;
    }

    @Override
    @Nonnull
    public Stream<String> getCountry() {
          @Nonnull val query = "SELECT country FROM users;";
          @Nullable ResultSet resultSet;
          @Nonnull List<String> list = new ArrayList<>();
        try {
            resultSet = connector.executeQuery(query);
            while (resultSet.next()) {
                @Nonnull val country = resultSet.getString("country");
                list.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Stream.empty();
        }
        return list.stream();
    }
}
