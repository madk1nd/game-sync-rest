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

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/")
@Api(value = "game synchronization", description = "Operations to synchronize user data")
public class SyncController {

    @Nonnull private final SyncService service;

    public SyncController(@Nonnull SyncService service) {
        this.service = service;
    }

    @Nonnull
    @ApiOperation(value = "Save user synchronization data")
    @RequestMapping(value = "/sync", method = POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseDTO> saveSyncData(@RequestParam("uuid") String uuid,
                                                    @RequestBody UserDTO dto) {
        return new ResponseEntity<>(service.saveUserData(uuid, dto), HttpStatus.OK);
    }

    @Nonnull
    @ApiOperation(value = "Operation to get stored user data")
    @RequestMapping(value = "/user", method = POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UserDTO> getUserInfo(@RequestParam("uuid") String uuid) {
        return new ResponseEntity<>(service.getUserInfo(uuid), HttpStatus.OK);
    }

    @Nonnull
    @ApiOperation(value = "Save user statistics")
    @RequestMapping(value = "/stat", method = POST, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ResponseDTO> test(@RequestParam("activity") Integer activity,
                                       @RequestParam("uuid") String uuid) {
        return new ResponseEntity<>(service.saveActivity(uuid, activity), HttpStatus.OK);
    }
}
