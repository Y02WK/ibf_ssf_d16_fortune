package ssf.iss.nus.restfortunecookie.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import ssf.iss.nus.restfortunecookie.services.FortuneCookie;

@RestController
@RequestMapping(path = "/cookies", produces = MediaType.APPLICATION_JSON_VALUE)
public class FortuneCookieController {
    @Autowired
    private FortuneCookie fc;

    private Logger logger = Logger.getLogger(FortuneCookieController.class.getName());

    @GetMapping
    public ResponseEntity<String> getCookies(@RequestParam(defaultValue = "1") Integer count) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonObject body;

        // check if input is outside of range. don't be greedy, leave some cookies for
        // others!
        if (count > 10 || count < 1) {
            body = objectBuilder.add("error", "count must be between 1-10 inclusive").build();
            return ResponseEntity.badRequest().body(body.toString());
        }

        final JsonArray array = Json.createArrayBuilder(fc.getCookies(count)).build();
        body = objectBuilder
                .add("cookie", array)
                .add("timestamp", System.currentTimeMillis())
                .build();

        return ResponseEntity.ok(body.toString());
    }
}
