package com.cme.msg.acceptancetest.stepdef;

import cucumber.api.java.After;
import cucumber.api.java.Before;

public class Hooks {

    @Before
    public void beforeScenario(){
        System.out.println("************************Start Testing*******************");

    }

    @After("@smoke")
    public void afterScenario(){
        System.out.println("************************Stop Testing*******************");

    }


    @Before("@smoke")
    public void beforeSmokeScenario(){
        System.out.println("************************Start Smoke Testing*******************");

    }

}
