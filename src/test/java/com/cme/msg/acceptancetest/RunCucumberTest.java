package com.cme.msg.acceptancetest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = { "pretty", "json:target/cucumber/cucumber.json",
      "junit:target/cucumber/cucumber-junit.xml"},
        strict = true,
        tags = {
                "@smoke",
                "~@not-ready"

},
        glue = {"com.cme.msg.acceptancetest.stepdef"},
        features = {
                "src/main/resources/smoke/UI-Test.feature"
        })

public class
RunCucumberTest extends CucumberRunner {
}
