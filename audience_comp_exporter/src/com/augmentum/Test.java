package com.augmentum;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Test {
    
    static Log log = LogFactory.getLog(Test.class);
    
    public static String htmEncode(String target)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int j = target.length();
        for (int i = 0; i < j; i++)
        {
            char c = target.charAt(i);
            switch (c)
            {
            case 60:
                stringbuffer.append("&lt;");
                break;
            case 62:
                stringbuffer.append("&gt;");
                break;
            case 38:
                stringbuffer.append("&amp;");
                break;
            case 34:
                stringbuffer.append("&quot;");
                break;
            case 169:
                stringbuffer.append("&copy;");
                break;
            case 174:
                stringbuffer.append("&reg;");
                break;
            case 165:
                stringbuffer.append("&yen;");
                break;
            case 8364:
                stringbuffer.append("&euro;");
                break;
            case 8482:
                stringbuffer.append("&#153;");
                break;
            case 13:
                if (i < j - 1 && target.charAt(i + 1) == 10)
                {
                    stringbuffer.append("<br>");
                    i++;
                }
                break;
            case 32:
                if (i < j - 1 && target.charAt(i + 1) == ' ')
                {
                    stringbuffer.append(" &nbsp;");
                    i++;
                    break;
                }
            default:
                stringbuffer.append(c);
                break;
            }
        }
        return new String(stringbuffer.toString());
    }
    
    public void log() {
        log.debug("Debug info.");
        log.info("Info info");
        log.warn("Warn info");
        log.error("Error info");
        log.fatal("Fatal info");
     }
    
    public static void main(String[] args) throws Exception {
        String s1 = "Opt-Ins for Bullfrogs &amp; Butterflies Child Care Ctr";
        String s2 = "Opt-Ins for Bullfrogs & Butterflies Child Care Ctr";
        System.out.println(htmEncode(s1));
        System.out.println(htmEncode(s2));
        
        System.out.println(StringEscapeUtils.unescapeHtml4(s1));
        System.out.println(StringEscapeUtils.unescapeHtml4(s2));
        
        new Test().log();
        
        String fileName = "/photo/2012/11/21/0vu_20121121164145.JPG";
        int cha = fileName.lastIndexOf(".");
        String tmp1 = fileName.substring(0, cha);
        String tmp2 = fileName.substring(cha);
        System.out.println(tmp1 + tmp2);
        
        System.out.println();
    }
}
