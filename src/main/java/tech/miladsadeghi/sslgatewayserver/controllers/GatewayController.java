package tech.miladsadeghi.sslgatewayserver.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GatewayController {

    @PostMapping("/gateway-health-check")
    public ResponseEntity<String> gatewayHealthCheck(@RequestBody Map<String, Integer> requestBody) {
        return new ResponseEntity<>("Gateway servers works perfectly -> " + requestBody.toString(), HttpStatus.OK);
    }
}
