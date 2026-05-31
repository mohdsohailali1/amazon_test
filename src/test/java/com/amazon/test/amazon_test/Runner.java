package com.amazon.test.amazon_test;

import org.junit.runner.RunWith;		
import cucumber.api.CucumberOptions;		
import cucumber.api.junit.Cucumber;		

@RunWith(Cucumber.class)				
@CucumberOptions(features="Features",glue={"com.amazon.test.amazon_test"})

public class Runner {

}