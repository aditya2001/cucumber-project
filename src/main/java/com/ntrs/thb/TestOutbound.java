package com.ntrs.thb;

import Utils.Lib;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



    public class TestOutbound {
    public static String getURL = "";
    public static String postURL = "";


    @Test(enabled = true, priority = 1)
    public void testOrders() throws Exception {
        String MTmessages = "MT515tosetr-006and051//setr006";
//        List<String> MTfiles = Lib.getFiles("./test-output/InboundMessages/UBS/T1/MT515tosetr-006and051/setr006");

    }

    @Parameters({"environment", "application"})
    @BeforeSuite
    public void beforeSuite(String environment, String application) throws Exception {

        getURL = Lib.ReadConfigData(environment, application).getProperty("getURL");
        postURL = Lib.ReadConfigData(environment, application).getProperty("postURL");

    }
}
