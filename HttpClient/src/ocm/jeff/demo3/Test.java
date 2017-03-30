package ocm.jeff.demo3;

import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Test {
    public static void main(String[] args) throws Exception {
        byte[] buffer = null;
        HttpResponse response;
        HttpClient client = new DefaultHttpClient();
        String uri = "http://www.channel4000.com.mtss-web.lakana-qa.com/breaking-news?newsletterStoryId=262594392";
        HttpGet getMethod = new HttpGet(uri);
        response = client.execute(getMethod);
        HttpEntity entity = response.getEntity();
        buffer = IOUtils.toByteArray(entity.getContent());
//        System.out.println(response);
//        System.out.println(entity);
//        System.out.println(buffer);
        System.out.println(new String(buffer));
        FileOutputStream fos;
        fos = new FileOutputStream("out.html");
        fos.write(buffer);
        fos.flush();
        fos.close();
    }
}
