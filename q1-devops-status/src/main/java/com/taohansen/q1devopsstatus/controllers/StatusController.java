package com.taohansen.q1devopsstatus.controllers;

import com.taohansen.q1devopsstatus.dto.StatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/status")
public class StatusController {
    private final Environment environment;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping
    public ResponseEntity<StatusDTO> getStatus() {
        StatusDTO status = new StatusDTO();
        status.setPort(environment.getProperty("local.server.port"));
        status.setApplicationName(appName);
        status.setStatus("Service is active!");
        return ResponseEntity.ok(status);
    }
}