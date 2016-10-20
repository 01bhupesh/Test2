package com.alta.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import com.alta.provider.Provider;

public class ConnectionProvider {
	private static  Connection con=null;
	private static ConnectionProvider provider=null;
	private  ConnectionProvider() throws Exception {
		getConnection();
		;}
	/**
	 * 
	 * @return Connection
	 * @throws Exception
	 */
	 private  Connection  getConnection() throws Exception{
		 Class.forName(Provider.DRIVER);
		 ConnectionProvider.con = DriverManager.getConnection(Provider.URL,Provider.USER,Provider.PASSWORD);
		 return con;
	 }
	 public static Connection getCon() {
		return con;
	}
	public static void setCon(Connection con) {
		ConnectionProvider.con = con;
	}
	public static ConnectionProvider createConnection() throws Exception{
		if(provider==null)
			provider=new ConnectionProvider();
		return provider;
		 
	 }
}
