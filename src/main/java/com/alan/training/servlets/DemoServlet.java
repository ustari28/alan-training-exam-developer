/**
 * 
 */
package com.alan.training.servlets;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.urlfetch.FetchOptions;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.HTTPResponse;
import com.google.appengine.api.urlfetch.URLFetchService;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

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
		URLFetchService fetchService = URLFetchServiceFactory
		        .getURLFetchService();
		HTTPRequest request = new HTTPRequest(new URL(
		        "https://alan-training-exam-developer-b.appspot.com/channel"),
		        HTTPMethod.GET, FetchOptions.Builder.doNotFollowRedirects()
		                .setDeadline(10000D));
		HTTPResponse response = fetchService.fetch(request);
		final String respuesta = new String(response.getContent());
		LOG.info("resp." + respuesta);
		resp.getWriter().write(respuesta);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		URLFetchService fetchService = URLFetchServiceFactory
		        .getURLFetchService();
		HTTPRequest request = new HTTPRequest(new URL(
		        "https://alan-training-exam-developer-b.appspot.com/channel?token="
		                + req.getParameter("token") + "&mensaje="
		                + req.getParameter("mensaje")), HTTPMethod.POST,
		        FetchOptions.Builder.doNotFollowRedirects().setDeadline(10000D));
		HTTPResponse response = fetchService.fetch(request);
	}
}
