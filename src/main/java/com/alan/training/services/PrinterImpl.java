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
public class PrinterImpl implements Device {

    @GAEResource(service = "A4Impl")
    private Paper A4Paper;

    /*
     * (non-Javadoc)
     * 
     * @see com.alan.training.services.Device#who()
     */
    public void who() {
        System.out.println("I'm printer");
        A4Paper.hello();
    }

}
