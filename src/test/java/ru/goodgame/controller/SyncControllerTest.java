package ru.goodgame.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.goodgame.controller.mock.SyncServiceMock;
import ru.goodgame.controller.mock.UserJsonModule;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.goodgame.utils.Constants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = SyncController.class)
@ContextConfiguration(classes = SyncApplicationTestConfig.class)
public class SyncControllerTest {

    @InjectMocks
    private SyncController controller;

    @Autowired
    private UserJsonModule module;

    private MockMvc mockMvc;

    @Spy
    private SyncServiceMock serviceMock;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new
                MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setObjectMapper(new ObjectMapper().registerModule(module).setPropertyNamingStrategy(
                PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES));
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setMessageConverters(
                mappingJackson2HttpMessageConverter).build();
    }

    @Test
    public void saveSyncData() throws Exception {
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
    public void getUserInfo() throws Exception {
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
    public void saveStatistics() throws Exception {
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