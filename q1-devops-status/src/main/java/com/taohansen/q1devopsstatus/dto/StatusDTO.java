package com.taohansen.q1devopsstatus.dto;

import lombok.Data;

@Data
public class StatusDTO {
    private String applicationName;
    private String port;
    private String status;
}