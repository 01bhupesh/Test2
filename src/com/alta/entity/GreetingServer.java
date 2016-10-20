package com.alta.entity;

import java.net.*;
import java.io.*;

import com.sun.security.ntlm.Client;

import sun.misc.Cleaner;

public class GreetingServer extends Thread
{   
	public GreetingServer(){
		      try
		      {
		         Socket client = new Socket("192.168.3.237", 8888);
		         System.out.println("IsConnected:"+client.isConnected());
		         System.out.println("Just connected to " + client.getRemoteSocketAddress());
		         OutputStream outToServer = client.getOutputStream();
		         DataOutputStream out = new DataOutputStream(outToServer);
		         out.writeUTF("Hello from~ShutDown ");
//		         InputStream inFromServer = client.getInputStream();
//		         DataInputStream in = new DataInputStream(inFromServer);
//		         System.out.println("Server says===========>>>" + in.readUTF());
		         client.close();
		      }catch(Exception e)
		      {
		         e.printStackTrace();
		      }
		   }
   public static void main(String [] args)
   {
	   GreetingServer greetingServer=  new GreetingServer();
   }
}