package com.taohansen.q1devopsstatus.config;

import com.taohansen.q1devopsstatus.dto.StatusDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Q1-DEVOPS-STATUS")
public interface StatusServiceClient {

    @GetMapping("/status")
    StatusDTO getStatus();
}