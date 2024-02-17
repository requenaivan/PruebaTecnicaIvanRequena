package com.inditex.mordor.controller;

import com.inditex.mordor.common.constant.Route;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;

@RestController
@RequestMapping(value = {(Route.HEALTH)}, produces = {(MediaType.APPLICATION_JSON_VALUE)})
public class HealthCheckController {

    @GetMapping
    @ResponseBody
    private ResponseEntity<HashMap<String, String>> healthCheck(){

        return new ResponseEntity<>(
                new HashMap<>() {{
                    put("status", "OK");
                }}, HttpStatus.OK);
    }
}
