/**
 * 
 */
package com.alan.training.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alan.training.model.Greeting;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.googlecode.objectify.ObjectifyService;

/**
 * @author alangabriel
 * 
 */
public class SignGuestbookServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6029372841698834417L;

	/*
	 * (non-Javadoc)
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {
		Greeting greeting;

		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		String guestbookName = req.getParameter("guestbookName");
		String content = req.getParameter("content");
		if (user != null) {
			greeting = new Greeting(guestbookName, content, user.getUserId(),
			        user.getEmail());
		} else {
			greeting = new Greeting(guestbookName, content);
		}

		// Use Objectify to save the greeting and now() is used to make the call
		// synchronously as we
		// will immediately get a new page using redirect and we want the data
		// to be present.
		ObjectifyService.ofy().save().entity(greeting).now();

		resp.sendRedirect("/index.jsp?guestbookName=" + guestbookName);
	}
}
