package ru.goodgame.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.goodgame.model.SyncService;

import javax.annotation.Nonnull;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/")
@Api(value = "game synchronization", description = "Operations to synchronize user data")
public class SyncController {
    @Nonnull private final SyncService service;

    public SyncController(@Nonnull SyncService service) {
        this.service = service;
    }

    @Nonnull
    @ApiOperation(value = "")
    @RequestMapping(value = "/test", method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> test(@RequestParam("test") String test) {
        return new ResponseEntity<>(service.getMoney().collect(Collectors.joining(", ")), HttpStatus.OK);
    }
}
