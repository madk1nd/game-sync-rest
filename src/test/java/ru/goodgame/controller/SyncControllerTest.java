package ru.goodgame.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.goodgame.controller.mock.SyncServiceMock;
import ru.goodgame.dto.UserDTO;
import ru.goodgame.serializer.UserDesirializer;
import ru.goodgame.serializer.UserSerializer;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.goodgame.utils.Constants.URL_STATISTICS;
import static ru.goodgame.utils.Constants.URL_USER_INFO;
import static ru.goodgame.utils.Constants.URL_USER_SYNC;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = SyncController.class)
@ContextConfiguration(classes = SyncApplicationTestConfig.class)
class SyncControllerTest {

    @InjectMocks
    private SyncController controller;

    private MockMvc mockMvc;

    @Service
    public class BasketItemJsonModule extends SimpleModule {
        public BasketItemJsonModule() {
            this.addDeserializer(UserDTO.class, new UserDesirializer());
            this.addSerializer(UserDTO.class, new UserSerializer());
        }
    }

    @Spy
    private SyncServiceMock serviceMock;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new
                MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(new ObjectMapper().registerModule(new BasketItemJsonModule()).setPropertyNamingStrategy(
                PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES));
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(
                mappingJackson2HttpMessageConverter).build();
    }

    @Test
    @DisplayName("Sync User data endpoint test")
    void saveSyncData() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(URL_USER_SYNC)
                        .param("uuid", "hash-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"country\":\"ru\",\"money\": 15}")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":\"OK\",\"message\":\"sync data was saved\",\"data\":\"\"}"))
                .andReturn();
    }

    @Test
    @DisplayName("Get User info endpoint test")
    void getUserInfo() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(URL_USER_INFO)
                        .param("uuid", "hash-2")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":\"OK\",\"message\":\"sync data was saved\",\"data\":{\"money\":2000,\"country\":\"UA\"}}"))
                .andReturn();
    }

    @Test
    @DisplayName("Statistics endpoint test")
    void saveStatistics() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post(URL_STATISTICS)
                        .param("uuid", "hash-2")
                        .param("activity", "2000")
        )
                .andExpect(status().isOk())
                .andExpect(content().string("{\"result\":\"OK\",\"message\":\"statistic data was saved\",\"data\":\"\"}"))
                .andReturn();
    }

}