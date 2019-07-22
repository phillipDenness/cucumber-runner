package com.phillip.denness.cucumber.runner.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("endpoint.daily-fund")
public class DailyFundProperties {

    private String host;
    private String port;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
