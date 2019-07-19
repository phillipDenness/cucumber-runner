package com.phillip.denness.cucumber.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features="classpath:features",
        glue = {"com/phillip/denness/cucumber/runner/steps"})
public class TestRunner {
}
