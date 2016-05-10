/**
 * 
 */
package com.alan.training.services;

import com.alan.training.core.GAEResource;
import com.alan.training.core.GAEService;

/**
 * @author alan
 *
 */
@GAEService
public class ServiceDAOImpl implements ServiceDAO {

    @GAEResource(service = "MouseImpl")
    private Device deviceMouse;

    @GAEResource(service = "PrinterImpl")
    private Device printerDivice;

    public void hello() {
        System.out.println("say hello");
        deviceMouse.who();
        printerDivice.who();
    }

}
