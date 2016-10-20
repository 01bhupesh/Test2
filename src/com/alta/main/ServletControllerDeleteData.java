package com.alta.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

@WebServlet("/ServletControllerDeleteData")
public class ServletControllerDeleteData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletControllerDeleteData() {
		super();

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Delete Data Called");
		StringBuilder sb = new  StringBuilder();
		BufferedReader br = request.getReader();
		System.out.println(request.getRequestURL());
		String str = null;
		while  ((str = br.readLine()) != null) {
			sb.append(str);
		}
		Object obj=JSONValue.parse(sb.toString()); 
		JSONObject jsonObject = (JSONObject) obj;  

		Map responseResult=new HashMap();
		Connection c = null;
		try {
			Class.forName("org.postgresql.Driver");
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bhupesh","postgres", "");
			Statement statement = c.createStatement();
			if(jsonObject!=null){
				String firstName = (String) jsonObject.get("firstName");  
				String lastName  = (String) jsonObject.get("lastName"); 
				if(!firstName.equals("")||!firstName.isEmpty()||!lastName.equals("")||!lastName.isEmpty()){
					statement.executeUpdate("Delete from employee where firstname='"+firstName+"' and lastname='"+lastName+"'");
					Map responseMsg=new HashMap();
					responseMsg.put("OK", "OK");
					responseResult.put("responseMsg", responseMsg);
				}
				else
				{
					Map responseMsg=new HashMap();
					responseMsg.put("OK", "Something went wrong..!");
					responseResult.put("responseMsg", responseMsg);
				}
			}
			else
			{
				Map responseMsg=new HashMap();
				responseMsg.put("OK", "Select a row first..!");
				responseResult.put("responseMsg", responseMsg);
			}
			response.getWriter().write(new JSONObject(responseResult).toJSONString());		
		} catch (Exception e) {	
			System.out.println(e);
		}
	}
}
