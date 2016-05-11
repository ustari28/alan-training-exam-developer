/**
 * 
 */
package com.alan.training.spring;

import org.springframework.stereotype.Service;

/**
 * @author alan
 *
 */
@Service("PersonalComputerImpl")
public class PersonalComputerImpl implements ISpringService {

    /*
     * (non-Javadoc)
     * 
     * @see com.alan.training.spring.ISpringService#sayHello()
     */
    public void sayHello() {
        System.out.println("I'm a machine");

    }

}
