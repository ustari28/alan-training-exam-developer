/**
 * 
 */
package com.alan.tasks.proccess;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Test Proccess.
 * 
 * @author alan
 *
 */
public class TestProccess extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -1929594299458084147L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("proccessing");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("proccessing");
    }
}
