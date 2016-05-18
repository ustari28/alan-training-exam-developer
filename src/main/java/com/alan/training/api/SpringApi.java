/**
 * 
 */
package com.alan.training.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alan.training.model.Token;
import com.alan.training.spring.ISpringService;

/**
 * @author alan
 *
 */
@RestController(value = "/spring")
public class SpringApi {

    @Autowired
    @Qualifier("PersonalComputerImpl")
    private ISpringService pcImpl;

    @RequestMapping(value = "/hola", method = RequestMethod.GET)
    public Token api() {
        Token t = new Token();
        t.setId("alan");
        pcImpl.sayHello();
        return t;
    }

    @RequestMapping(value = "/hola", method = RequestMethod.POST)
    public Token apiPost(@RequestBody Token t) {
        pcImpl.sayHello();
        System.out.println(t.toString());
        return t;
    }
}
