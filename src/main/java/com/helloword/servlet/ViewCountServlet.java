package com.helloword.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewCountServlet
 */
@WebServlet("/ViewCount")
public class ViewCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Long totalVisited;
    /**
     * Default constructor. 
     */
    public ViewCountServlet() {
    	this.totalVisited = 0L;
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		var a = Integer.parseInt(request.getParameter("a"));
		var b = Integer.parseInt(request.getParameter("b"));
		// TODO Auto-generated method stub
		var out = response.getWriter();
		out.append("<h1> Total Visited: </h1>" + totalVisited++);
		out.append("<h1> Total a + b: </h1>" + (a +b));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
