package com.alta.main;

import java.io.BufferedReader;
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

@WebServlet("/ServletControllerEditData")
public class ServletControllerEditData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletControllerEditData() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Edit Data Called");
		StringBuilder sb = new  StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		while  ((str = br.readLine()) != null) {
			sb.append(str);
		}
		Object obj=JSONValue.parse(sb.toString()); 
		JSONObject jsonObject = (JSONObject) obj;  

		String firstName = (String) jsonObject.get("firstName");  
		String lastName  = (String) jsonObject.get("lastName"); 

		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bhupesh","postgres", "");
			Statement statement = c.createStatement();
			if(!firstName.equals("")||!firstName.isEmpty()||!lastName.equals("")||!lastName.isEmpty()){
				statement.executeUpdate("Update employee where firstname='"+firstName+"' and lastname='"+lastName+"'");

			ResultSet rs = statement.executeQuery("Select * from employee");
			List  list=new ArrayList();
			while(rs.next()){
				jsonObject=new JSONObject();
				jsonObject.put("lastName",rs.getObject(1).toString());    
				jsonObject.put("firstName",rs.getObject(2).toString());
				list.add(jsonObject);	
			}
			response.getWriter().write(JSONValue.toJSONString(list));
			}

		} catch (Exception e) {	
			System.out.println(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Send Data Called");
		StringBuilder sb = new  StringBuilder();
		BufferedReader br = request.getReader();
		String str = null;
		while  ((str = br.readLine()) != null) {
			sb.append(str);
		}
		System.out.println(sb);
	}

}
