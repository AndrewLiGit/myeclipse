package ocm.jeff.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.lang.StringUtils;

public class Util {

    public static String doUploadFile(File file, String url) {
        String response = "";
        if (!file.exists()) {
            return "File Not Exists";
        }
        PostMethod postMethod = new PostMethod(url);
        try {
            // FilePart 用来上传文件的类, file要上传的文件
            FilePart fp = new FilePart("file", file);
            Part[] parts = { fp };
            // 对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
            MultipartRequestEntity mre = new MultipartRequestEntity(parts, postMethod.getParams());
            postMethod.setRequestEntity(mre);
            // ---------------------------------------------
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(50000);// 由于要上传的文件可能比较大

            int status = client.executeMethod(postMethod);

            if (status == HttpStatus.SC_OK) {
                InputStream inputStream = postMethod.getResponseBodyAsStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = bufferedReader.readLine()) != null) {
                    stringBuffer.append(str);
                }
                response = stringBuffer.toString();
            } else {
                response = "Fail";
            }
        } catch (Exception e) {
            
        } finally{
            postMethod.releaseConnection();
        }
        return response;
    }

    /**
     * 
     * @param postUrl
     *            用户要访问的 url 地址
     * @return
     */
    public static String doUploadFile(String postUrl) {
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
