package vip.mate.gateway.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger.web.*;
import vip.mate.gateway.config.SwaggerResourceConfig;

import java.util.List;

@RestController
@RequestMapping("/swagger-resources")
@Profile({"dev","test","local"})
public class SwaggerHandler {

    private SwaggerResourceConfig swaggerResourceConfig;
    @Autowired
    public SwaggerHandler(SwaggerResourceConfig swaggerResourceConfig) {
        this.swaggerResourceConfig = swaggerResourceConfig;
    }

    @RequestMapping(value = "/configuration/security")
    public ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping(value = "/configuration/ui")
    public ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
    }

    @RequestMapping
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return new ResponseEntity<>(swaggerResourceConfig.get(), HttpStatus.OK);
    }
}
