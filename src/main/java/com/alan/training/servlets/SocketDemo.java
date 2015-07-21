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

import com.google.appengine.api.channel.ChannelMessage;
import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

/**
 * @author alangabriel
 * 
 */
public class SocketDemo extends HttpServlet {

	/**
	 * Serial id.
	 */
	private static final long serialVersionUID = 4223718539164959365L;
	private static final Logger LOG = Logger.getLogger(SocketDemo.class
	        .getSimpleName());

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws IOException {
		LOG.info("inicio");
		ChannelService channelService = ChannelServiceFactory
		        .getChannelService();
		String token = channelService.createChannel("xxxxxxxx1");
		LOG.info("token:" + token);
		resp.setContentType("text/html");
		resp.getWriter().write(token);
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
		ChannelService channelService = ChannelServiceFactory
		        .getChannelService();
		LOG.info(req.getParameter("token") + "...."
		        + req.getParameter("mensaje"));
		final ChannelMessage channelMessage = new ChannelMessage(
		        req.getParameter("token"), req.getParameter("mensaje"));
		channelService.sendMessage(channelMessage);
	}
}
