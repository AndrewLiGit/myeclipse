package ocm.jeff.demo;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        File file = new File("sources/test.txt");
        String body = Util.doUploadFile(file,"http://www.baidu.com");
        System.out.println(body);
    }
}
