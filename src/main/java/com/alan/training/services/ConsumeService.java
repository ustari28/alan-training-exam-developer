package com.alan.training.services;

import com.alan.training.core.GAEResource;
import com.alan.training.core.GAEScan;

@GAEScan
public class ConsumeService {

    @GAEResource(service = "ServiceDAOImpl")
    private ServiceDAO servicio;
    private ServiceDAO servicio1;

    public ConsumeService() {
        WizzardService.getInstance().wizzard(this);
    }

    public void start() {
        servicio.hello();
    }
}
