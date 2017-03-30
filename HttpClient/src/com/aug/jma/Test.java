package com.aug.jma;

import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.aug.jma.util.HttpClientUtil;
import com.aug.jma.util.HttpURLConnectionUtil;

public class Test {
    public static void main(String[] args) throws HttpException, IOException {
        String responceString = HttpURLConnectionUtil.getResponce("http://www.baidu.com");
        System.out.println(responceString);
    }
}
