package com.ntrs.thb;

import Utils.Lib;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TestOutbound{
    public String env="";
    public String app="";
    public static String username="";
    public static String password="";
    public Map<String, String> setr006BizMsgIdrs = new HashMap<String, String>();
    public Map<String, String> setr051BizMsgIdrs = new HashMap<String, String>();
    Map <Integer, Object[] > transactionResults = new TreeMap<Integer, Object[] >();
    public static int counter=1;


    @Test(enabled = true, priority = 1)
    public void testOutboundMessagesetr006() throws Exception {
        String MTmessages = "MT515tosetr-006and051//setr006";
//        List<String> MTfiles = Lib.getFiles("./test-output/InboundMessages/UBS/T1/MT515tosetr-006and051/setr006");
//        List<String> fileNames = Lib.getFiles("./test-output/InboundMessages/UBS/t2/MT515tosetr-006and51/setr006");


    }}
