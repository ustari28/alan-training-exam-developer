package com.alan.training.api;

import com.alan.training.providers.AnnotationLoadContextListener;
import com.alan.training.services.ConsumeService;

public class Main {

    public Main() {
        AnnotationLoadContextListener ann = new AnnotationLoadContextListener();
        ann.contextInitialized(null);
        ConsumeService consume = new ConsumeService();
        consume.start();
    }

    public static void main(String[] args) {
        new Main();
    }
}
