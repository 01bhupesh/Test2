package com.alta.entity;

import java.util.Hashtable;

public class ConnectionInfo {
	private  ConnectionInfo connectionInfo;
	public static String DRIVERCLASS;
	public static String URL;
	public static String USER;
	public static String PASSWORD;
	public ConnectionInfo getInstance(){
		if(connectionInfo==null)
			connectionInfo=new ConnectionInfo();
		return connectionInfo;
	}
	public static void loadConfig(Hashtable hashtable){
		DRIVERCLASS=hashtable.get("DRIVER").toString();
		URL=hashtable.get("URL").toString();
		USER=hashtable.get("USER").toString();
		PASSWORD=hashtable.get("PASSWORD").toString();
	}
}
