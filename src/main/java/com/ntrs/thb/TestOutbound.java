package com.ntrs.thb;

import Utils.Lib;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestOutbound {
    public static String getURL = "";
    public static String postURL = "";
    public static String TOKEN_REQUEST_URL = "";
    public static String CLIENT_ID = "";
    public static String CLIENT_SECRET = "";
    public static String REDIRECT_URI = "";
//14H608399V869045G
        //https://api.sandbox.paypal.com/v2/checkout/orders/14H608399V869045G

        //https://api.sandbox.paypal.com/v2/checkout/orders

    @Test(enabled = true, priority = 1)
    public void testOrders() throws Exception {
        List<String> fileNames = Lib.getFiles("src/main/resources/Data/");
        for (String fileName : fileNames) {

            File file = new File("fileName");
          String jsonString = "{'header': {'alerts': [{'AlertID': '2', 'TSExpires': null, 'Target': '1', 'Text': 'woot', 'Type': '1'}, {'AlertID': '3', 'TSExpires': null, 'Target': '1', 'Text': 'woot', 'Type': '1'}], 'session': '0bc8d0835f93ac3ebbf11560b2c5be9a'}, 'result': '4be26bc400d3c'}";

           Map<String, Object> hm = Lib.convertJsonToMap(jsonString);
            System.out.println(hm);

//            String orderId = Lib.httpPost(fileName, postURL);
//            System.out.println(orderId);
        }
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
