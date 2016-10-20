package com.alta.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;

import com.alta.connection.ConnectionProvider;
import com.alta.entity.Student;
public class Main {
	static ArrayList arrayList =new ArrayList();
	static Hashtable hashtable;
	public Main() {;}
	public static void main(String[] args) {
		try{
			String str ="[";
			Connection con = ConnectionProvider.createConnection().getCon();
			Statement statement =con.createStatement();
			ResultSet rs = statement.executeQuery("select * from student");
			while(rs.next()){
				str=str+"{"+'"'+"firstName"+'"'+":"+'"'+rs.getString(1)+'"'+",\n"+'"'+"lastName"+'"'+":"+'"'+rs.getString(2)+'"'+",\n"+'"'+"id"+'"'+":"+'"'+rs.getString(3)+'"'+"},\n";
			}
			str = str.substring(0,str.length()-2);
			str=str+"]";
			System.out.println(str);
			FileOutputStream fos =new FileOutputStream("E://Swing/WebAngulerJs/WebContent/student.txt");
			fos.write(str.getBytes());
			con.close();  
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
