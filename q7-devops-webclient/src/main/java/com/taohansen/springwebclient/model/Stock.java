package com.taohansen.springwebclient.model;

import lombok.Data;

import java.util.Date;

@Data
public class Stock {
    public String currency;
    public String shortName;
    public String longName;
    public double regularMarketChange;
    public double regularMarketChangePercent;
    public Date regularMarketTime;
    public double regularMarketPrice;
    public int regularMarketDayHigh;
    public String regularMarketDayRange;
    public int regularMarketDayLow;
    public int regularMarketVolume;
    public double regularMarketPreviousClose;
    public double regularMarketOpen;
    public String fiftyTwoWeekRange;
    public int fiftyTwoWeekLow;
    public int fiftyTwoWeekHigh;
    public String symbol;
    public double priceEarnings;
    public double earningsPerShare;
    public String logourl;
}