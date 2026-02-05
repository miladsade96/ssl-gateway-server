package tech.miladsadeghi.sslgatewayserver.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GatewayController {

    @GetMapping("/gateway-health-check")
    public ResponseEntity<String> gatewayHealthCheck() {
        return new ResponseEntity<>("Gateway servers works perfectly", HttpStatus.OK);
    }
}
