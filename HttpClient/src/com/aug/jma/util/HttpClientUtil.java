package com.aug.jma.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;

public class HttpClientUtil {
    public static String getResponce(String postUrl) {
        if (StringUtils.isEmpty(postUrl)) {
            return null;
        }
        String respon = "";
        GetMethod postMethod = new GetMethod(postUrl);
        try {
            HttpClient client = new HttpClient();
            // 设置proxy
            // HostConfiguration config = httpClient.getHostConfiguration();
            // config.setProxy("192.168.179.53", 8888);
            // 设置链接时间
            client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);
            int status = client.executeMethod(postMethod);
            // 返回结果是200
            if (status == HttpStatus.SC_OK) {
                InputStream inputStream = postMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                respon = stringBuffer.toString();
            } else {
                respon = "failed";
            }
        } catch (Exception e) {
            // TODO: handle exception
        } finally {
            postMethod.releaseConnection();
        }
        return respon;
    }
}
