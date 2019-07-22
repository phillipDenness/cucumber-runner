package com.phillip.denness.cucumber.runner;

import com.phillip.denness.cucumber.runner.config.DailyFundProperties;
import org.junit.runner.JUnitCore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DailyFundProperties.class)
public class Application {
    public static void main(String[] args) throws Exception {
        JUnitCore.main("com.phillip.denness.cucumber.runner.TestRunner");
    }
}
