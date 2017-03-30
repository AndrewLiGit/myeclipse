package com.aug.jma.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpURLConnectionUtil {
    public static String getResponce(String urlString){
        String responce = "";
        try {
            URL url = new URL(urlString);
            //Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PropertyUtil.getProperty("proxyhost"),
            //        Integer.parseInt(PropertyUtil.getProperty("proxypost"))));
            HttpURLConnection uc = (HttpURLConnection) url.openConnection();
            uc.connect();
            InputStream inputStream = uc.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String str = "";
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            responce = stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responce;
    }
}
