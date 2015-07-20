/**
 * 
 */
package com.alan.training.servlets;

import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author alangabriel
 * 
 */
public class TestSignURL extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(TestSignURL.class
	        .getSimpleName());

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		Enumeration e = req.getHeaderNames();
		Enumeration atributos = req.getAttributeNames();
		LOG.info("headers");
		while (e.hasMoreElements()) {
			String headerName = String.valueOf(e.nextElement());
			LOG.info(headerName + ":" + req.getHeader(headerName));
		}
		LOG.info("attrs");
		while (atributos.hasMoreElements()) {
			String atributoName = String.valueOf(atributos.nextElement());
			LOG.info(atributoName + ":" + req.getAttribute(atributoName));
		}
		resp.setStatus(200);
		resp.getWriter().write("ENTERADO");
	}
}
