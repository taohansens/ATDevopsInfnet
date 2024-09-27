package com.taohansen.q1devopsstatus.controllers;

import com.taohansen.q1devopsstatus.config.StatusServiceClient;
import com.taohansen.q1devopsstatus.dto.StatusDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoadBalancerTestController {
    final private StatusServiceClient client;

    @GetMapping("/test-loadbalanced")
    public ResponseEntity<StatusDTO> testLoadBalanced() {
        StatusDTO response = client.getStatus();
        return ResponseEntity.ok(response);
    }
}
