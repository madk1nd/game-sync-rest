package ru.goodgame.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.goodgame.dto.ResponseDTO;
import ru.goodgame.dto.UserDTO;
import ru.goodgame.model.SyncService;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static ru.goodgame.utils.Constants.URL_STATISTICS;
import static ru.goodgame.utils.Constants.URL_USER_INFO;
import static ru.goodgame.utils.Constants.URL_USER_SYNC;

@RestController
@RequestMapping("/")
@Api(value = "game synchronization", description = "Operations to synchronize user data")
public class SyncController {

    @Nonnull private final SyncService service;

    public SyncController(@Nonnull SyncService service) {
        this.service = service;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("RU");
        list.add("UK");
        list.add("UA");
        list.add("BL");
        list.add("GE");
        list.add("FR");
        list.add("KZ");
        list.add("UZ");
        list.add("JA");
        list.add("US");
        list.add("SP");
        list.add("IZ");
        list.add("AR");
        for (int i = 0; i < 100; i++) {
            System.out.println(list.get((int) (Math.random() * list.size())));
        }
    }
    @Nonnull
    @ApiOperation(value = "Save user synchronization data")
    @RequestMapping(value = URL_USER_SYNC, method = POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseDTO> saveSyncData(@RequestParam("uuid") String uuid,
                                                    @RequestBody UserDTO dto) {
        return new ResponseEntity<>(service.saveUserData(uuid, dto), HttpStatus.OK);
    }

    @Nonnull
    @ApiOperation(value = "Operation to get stored user data")
    @RequestMapping(value = URL_USER_INFO, method = POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseDTO> getUserInfo(@RequestParam("uuid") String uuid) {
        return new ResponseEntity<>(service.getUserInfo(uuid), HttpStatus.OK);
    }

    @Nonnull
    @ApiOperation(value = "Save user statistics")
    @RequestMapping(value = URL_STATISTICS, method = POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseDTO> saveStatistics(@RequestParam("activity") Integer activity,
                                                      @RequestParam("uuid") String uuid) {
        return new ResponseEntity<>(service.saveActivity(uuid, activity), HttpStatus.OK);
    }
}
