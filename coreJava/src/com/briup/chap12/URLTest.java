package com.briup.chap12;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLTest {
	public static void main(String[] args) {
		try {
			URL url = new URL("127.0.0.1");
			URLConnection connection = url.openConnection();
			System.out.println(connection.getContentEncoding());
			System.out.println(connection.getContentLength());
			System.out.println(connection.getConnectTimeout());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
