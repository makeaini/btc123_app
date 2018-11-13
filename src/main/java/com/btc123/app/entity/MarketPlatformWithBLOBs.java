package com.btc123.app.entity;

public class MarketPlatformWithBLOBs extends MarketPlatform {
    private String ratesDirections;

    private String summary;

    public String getRatesDirections() {
        return ratesDirections;
    }

    public void setRatesDirections(String ratesDirections) {
        this.ratesDirections = ratesDirections == null ? null : ratesDirections.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }
}