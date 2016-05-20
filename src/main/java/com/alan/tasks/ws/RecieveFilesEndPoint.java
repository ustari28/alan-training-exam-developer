/**
 * 
 */
package com.alan.tasks.ws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alan.tasks.entities.File;

/**
 * @author alan
 *
 */
@RestController("/spring/")
public class RecieveFilesEndPoint {

    @Value("${APP.ENTORNO}")
    String entorno;

    @RequestMapping(value = "/tasks/ws/recieve", method = RequestMethod.GET)
    public File recieve() {
        File f = new File();
        f.setName("recieve.txt");
        System.out.println(entorno);
        return f;
    }
}
