/**
 * 
 */
package com.alan.training.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author alan
 *
 */
@WebServlet("/servlet/test")
public class TestWDAnnotation extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = 7016855913521571401L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("TESTTTTTTTTTTTT");
    }
}
