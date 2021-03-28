package com.ntrs.thb;

import Utils.Lib;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;


public class TestOutbound {
    public static String getURL = "";
    public static String postURL = "";
    public static String TOKEN_REQUEST_URL = "";
    public static String CLIENT_ID = "";
    public static String CLIENT_SECRET = "";
    public static String REDIRECT_URI = "";
    private static final String LOG4J_PROPERTIES = "src/main/resources/UAT/log4j2.xml";

        //https://api.sandbox.paypal.com/v2/checkout/orders/14H608399V869045G

        //https://api.sandbox.paypal.com/v2/checkout/orders

    @Test(enabled = true, priority = 1)
    public void createOrder() throws Exception {

        List<String> fileNames = Lib.getFiles("src/main/resources/Data/");
        for (String fileName : fileNames) {
            String jsonString = Lib.readJsonFile(fileName);
            Map<String, Object> inputMap = Lib.convertJsonToMap(jsonString);
            inputMap.remove("create_time");
            System.out.println(inputMap);
            String orderId = Lib.httpPost(fileName, postURL);
            System.out.println(orderId);
            String output = Lib.httpGet(getURL, orderId);
            System.out.println(output);
            Map<String, Object> outputMap = Lib.convertJsonToMap(output);

                Lib.compareJSON(outputMap, inputMap);

        }
    }
    @BeforeTest
    public void beforeTest() throws Exception{
        System.setProperty("log4j.configurationFile", LOG4J_PROPERTIES);
    }

    @Parameters({"environment", "application"})
    @BeforeSuite
    public void beforeSuite(String environment, String application) throws Exception {



        getURL = Lib.ReadConfigData(environment, application).getProperty("getURL");
        postURL = Lib.ReadConfigData(environment, application).getProperty("postURL");
        TOKEN_REQUEST_URL = Lib.ReadConfigData(environment, application).getProperty("TOKEN_REQUEST_URL");
        CLIENT_ID = Lib.ReadConfigData(environment, application).getProperty("CLIENT_ID");
        CLIENT_SECRET = Lib.ReadConfigData(environment, application).getProperty("CLIENT_SECRET");
        REDIRECT_URI = Lib.ReadConfigData(environment, application).getProperty("REDIRECT_URI");

    }
}
