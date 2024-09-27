package com.taohansen.springwebclient.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;

@Data
public class Result {
    public ArrayList<Stock> results;
    public Date requestedAt;
    public String took;
}
