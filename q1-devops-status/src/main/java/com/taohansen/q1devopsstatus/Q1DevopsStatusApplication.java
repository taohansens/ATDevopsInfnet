package com.taohansen.q1devopsstatus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Q1DevopsStatusApplication {

    public static void main(String[] args) {
        SpringApplication.run(Q1DevopsStatusApplication.class, args);
    }

}
