package com.alan.training.services;

import com.alan.training.core.GAEService;

@GAEService
public class MouseImpl implements Device {

    public void who() {
        System.out.println("i'm mouse");

    }

}
