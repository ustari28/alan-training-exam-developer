/**
 * 
 */
package com.alan.training.servlets;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author alangabriel
 * 
 */
public class DemoServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6150775745760607022L;
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(DemoServlet.class
			.getSimpleName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		LOG.info("inicio");
		resp.setContentType("application/json");
		resp.getWriter().write("HELLO");
	}
}
