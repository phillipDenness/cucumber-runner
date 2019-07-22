package com.phillip.denness.cucumber.runner.model;

import java.io.Serializable;

public class DailyFundResponse implements Serializable{

    public DailyFundResponse() {}

    private String fund;

    private String date;

    private String price;

    private String difference;

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    @Override
    public String toString() {
        return "DailyFundResponse{" +
                "fund='" + fund + '\'' +
                ", date='" + date + '\'' +
                ", price='" + price + '\'' +
                ", difference='" + difference + '\'' +
                '}';
    }
}
