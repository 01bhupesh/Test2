package com.alta.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.postgresql.core.Query;

import com.alta.entity.Student;

/**
 * Servlet implementation class ServletController
 */
@WebServlet("/ServletController")
public class ServletController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletController() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURL());
		
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bhupesh","postgres", "");
			Statement statement = c.createStatement();
			ResultSet rs = statement.executeQuery("Select * from employee");
			List  list=new ArrayList();
			while(rs.next()){

				Object firstName = rs.getObject(1);
				Object lastName = rs.getObject(2);
				JSONObject obj=new JSONObject();
				obj.put("lastName",firstName.toString());    
				obj.put("firstName",lastName.toString());
				list.add(obj);			
			}
			response.getWriter().write(JSONValue.toJSONString(list));
		} catch (Exception e) {	
			System.out.println(e);
		}	
	}

}
