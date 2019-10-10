package com.gcit.MyServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.gcit.DTO.*;

/**
 * Servlet implementation class RestService
 */
@WebServlet({"/login","/login/","/login/*"})
public class RestService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Gson gson = new Gson();
		User sentinfo = gson.fromJson(request.getReader(),User.class);

		List<User> users = new ArrayList<User>();

		users.add(new User("Lamar", "abc123"));
		users.add(new User("Flash", "barryallen"));
		users.add(new User("Superman", "kryptonite"));
		users.add(new User("Batman", "joker123"));

		try {			
			response.setContentType("application/json");	

			List<User> list = users.stream()
					.filter(user -> (user.getUsername().equals(sentinfo.getUsername())) && 
							(user.getPassword().equals(sentinfo.getPassword())))
					.collect(Collectors.toList());

			PrintWriter out = response.getWriter();	

			if((!list.isEmpty()) && (list != null)) { 
				out.print("success");
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			}

			out.flush();
		}
		catch(Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}
		
}


