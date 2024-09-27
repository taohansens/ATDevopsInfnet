package com.taohansen.devopsspring_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DevOpsSpringEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevOpsSpringEurekaApplication.class, args);
    }

}
