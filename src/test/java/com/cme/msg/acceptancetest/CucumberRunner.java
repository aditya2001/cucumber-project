package com.cme.msg.acceptancetest;

import Utils.Lib;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public abstract class CucumberRunner {

public static String username;
public static String password;
public static String url;
private static final String LOG4J_PROPERTIES = "src/main/resources/INT/log4j2.xml";


    @BeforeClass
    public static void beforeRun() throws Exception {
    System.setProperty("log4j.configurationFile", LOG4J_PROPERTIES);
    username=Lib.ReadConfigData("INT", "THB").getProperty("USERNAME");
    password=Lib.ReadConfigData("INT", "THB").getProperty("PASSWORD");
    url=Lib.ReadConfigData("INT", "THB").getProperty("PASSWORD");
}

    @AfterClass
    public static void afterRun() throws Exception {
    System.out.println("***********Cucumber Execution Completed*****************");

    }



}
