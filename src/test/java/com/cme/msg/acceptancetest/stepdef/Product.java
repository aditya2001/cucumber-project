package com.cme.msg.acceptancetest.stepdef;

import java.util.Map;

//Encapsulation

class Product {
    private String Name;


    public String setName(Map<String, String> mp) {
        return this.Name = mp.get("Name");
    }

    public String getName() {
        return this.Name;
    }

}