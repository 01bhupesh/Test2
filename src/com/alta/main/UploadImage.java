package com.alta.main;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

import sun.awt.image.ByteArrayImageSource;

import com.alta.entity.Student;
import com.google.gson.Gson;

@WebServlet("/fileUpload")
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadImage() {
		super();

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Upload Data --------------------Called");
		
		/*try{
			
			StringBuilder sb = new  StringBuilder();
			BufferedReader bufferedReader = request.getReader();
//			char [] bytes = new char[1024];
//			br.read(bytes, 0, 10);
//			System.out.println(bytes.toString());
		
	        String str;
	        int numRead = 0;
			if (bufferedReader.ready()) {

                try {
                    while ((numRead = bufferedReader.read()) >= 0) {

                        //convert asci to char and then to string
                        str = String.valueOf((char) numRead);
                       

                        if ((str != null)&& (str.toString() != "")) {

                            sb.append(str);
                        }

                        if (!bufferedReader.ready()){
                            //no more characters to read
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/
   
	}
}