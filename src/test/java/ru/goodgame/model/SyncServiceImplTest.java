package ru.goodgame.model;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.goodgame.dto.ResponseDTO;
import ru.goodgame.dto.UserDTO;

import javax.annotation.Nonnull;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SyncServiceImplTest {

    private SyncServiceImpl service;

    @BeforeEach
    void setUp() throws SQLException {
        @Nonnull val userRepository = mock(UserRepositoryImpl.class);

        when(userRepository.getUserData("hash-1"))
                .thenReturn(new UserDTO("RU", 1000));
        when(userRepository.getUserData("unknown_uuid"))
                .thenReturn(null);
        when(userRepository.getUserData("exception"))
                .thenThrow(new SQLException("test"));

        when(userRepository.syncUserData("hash-3", new UserDTO("UK", 2000)))
                .thenReturn(true);
        when(userRepository.syncUserData("unknown_uuid", new UserDTO("UA", 3000)))
                .thenReturn(false);
        when(userRepository.syncUserData("exception", new UserDTO("UA", 3000)))
                .thenThrow(new SQLException("test"));

        @Nonnull val activityRepository = mock(ActivityRepositoryImpl.class);
        when(activityRepository.saveActivity("hash-5", 1000))
                .thenReturn(true);
        when(activityRepository.saveActivity("unknown_uuid", 1000))
                .thenReturn(false);
        when(activityRepository.saveActivity("exception", 2000))
                .thenThrow(new SQLException("test"));

        service = new SyncServiceImpl(userRepository, activityRepository);
    }

    @Test
    @DisplayName("Sync User data OK")
    void saveUserData() {
        ResponseDTO expected = ResponseDTO.goodResponse("sync data was saved", "");
        ResponseDTO actual = service.saveUserData("hash-3", new UserDTO("UK", 2000));

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Sync User data unknown user")
    void saveUserData_Fail() {
        ResponseDTO expected = ResponseDTO.goodResponse("sync data wasn't saved", "");
        ResponseDTO actual = service.saveUserData("unknown_uuid", new UserDTO("UA", 3000));

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Sync User data exception")
    void saveUserData_Exception() {
        ResponseDTO expected = ResponseDTO.badResponse("test");
        ResponseDTO actual = service.saveUserData("exception", new UserDTO("UA", 3000));

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get User info OK")
    void getUserInfo() {
        UserDTO dto = new UserDTO("RU", 1000);
        ResponseDTO expected = ResponseDTO.goodResponse("Successfully found data for user with id = hash-1", dto);
        ResponseDTO actual = service.getUserInfo("hash-1");

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get User info unknown uuid")
    void getUserInfo_Fail() {
        ResponseDTO expected = ResponseDTO.badResponse("Can't find data for user with id = unknown_uuid");
        ResponseDTO actual = service.getUserInfo("unknown_uuid");

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Get User info exception")
    void getUserInfo_Exception() {
        ResponseDTO expected = ResponseDTO.badResponse("test");
        ResponseDTO actual = service.getUserInfo("exception");

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Save User statistics Ok")
    void saveActivity() {
        ResponseDTO expected = ResponseDTO.goodResponse("statistic data was saved", "");
        ResponseDTO actual = service.saveActivity("hash-5", 1000);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Save User statistics unknown uuid")
    void saveActivity_Fail() {
        ResponseDTO expected = ResponseDTO.goodResponse("statistic data wasn't saved", "");
        ResponseDTO actual = service.saveActivity("unknown_uuid", 1000);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Save User statistics exception")
    void saveActivity_Exception() {
        ResponseDTO expected = ResponseDTO.badResponse("test");
        ResponseDTO actual = service.saveActivity("exception", 2000);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

}