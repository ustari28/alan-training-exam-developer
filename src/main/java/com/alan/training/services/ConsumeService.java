package com.alan.training.services;

import com.alan.training.core.GAEScan;
import com.alan.training.core.GAEService;

@GAEScan
public class ConsumeService {

    @GAEService(service = "ServiceDAOImpl")
    private ServiceDAO servicio;
    private ServiceDAO servicio1;

    public void start() {
        servicio.hello();
    }
}
