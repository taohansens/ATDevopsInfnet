package com.taohansen.q2devopscars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Q2DevopsCarsApplication {

    public static void main(String[] args) {
        SpringApplication.run(Q2DevopsCarsApplication.class, args);
    }

}
