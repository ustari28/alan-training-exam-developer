/**
 * 
 */
package com.alan.tasks.ws;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alan.tasks.entities.File;

/**
 * @author alan
 *
 */
@RestController("/task/ws")
public class RecieveFilesEndPoint {

    @RequestMapping(value = "/recieve", method = RequestMethod.GET)
    public File recieve() {
        File f = new File();
        f.setName("recieve.txt");
        return f;
    }
}
