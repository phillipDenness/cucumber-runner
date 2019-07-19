package com.phillip.denness.cucumber.runner.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@PropertySource("application.properties")
@ComponentScan("com.phillip.denness.cucumber.runner")
public class Config {
}