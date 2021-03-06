package com.alan.training.services;

import com.alan.training.core.GAEResource;
import com.alan.training.core.GAEScan;
import com.alan.training.core.IGAEService;

@GAEScan
public class ConsumeService implements IGAEService {

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
