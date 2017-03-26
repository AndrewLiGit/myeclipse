package com.lzjtu.bookstore.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	public static boolean isEmpty(String data) {
        if (data == null || data.equals("")) {
            return true;
        }
        return false;
    }

    public static String keywordChange(String keyword) {
        if (keyword != null) {
            keyword = replace(keyword, "%", "\\%");
            keyword = replace(keyword, "_", "\\_");
            // keyword = replace(keyword, "[", "\\[");
            // keyword = replace(keyword, "]", "\\]");
            // keyword = replace(keyword, "^", "\\^");
            // keyword = replace(keyword, "!", "\\!");
        }
        return keyword;
    }

    public static String htmlEncode(String txt) {
        if (txt != null) {
            txt = replace(txt, "&", "&amp;");
            txt = replace(txt, "&amp;amp;", "&amp;");
            txt = replace(txt, "&amp;quot;", "&quot;");
            txt = replace(txt, "\"", "&quot;");
            txt = replace(txt, "&amp;lt;", "&lt;");
            txt = replace(txt, "<", "&lt;");
            txt = replace(txt, "&amp;gt;", "&gt;");
            txt = replace(txt, ">", "&gt;");
            txt = replace(txt, "&amp;nbsp;", "&nbsp;");
//            txt = replace(txt, "%", "%25");
        }
        return doWithNull(txt);
    }

    public static String[] split(String strSource, String strDiv) {
        int arynum = 0, intIdx = 0, intIdex = 0;
        int div_length = strDiv.length();
        if (strSource.compareTo("") != 0) {
            if (strSource.indexOf(strDiv) != -1) {
                intIdx = strSource.indexOf(strDiv);
                    for (int intCount = 1; ; intCount++) {
                        if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
                            intIdx = strSource.indexOf(strDiv, intIdx + div_length);
                                arynum = intCount;
                         } else {
                             arynum += 2;
                             break;
                         }
                 }
            } else {
                arynum = 1;
            }
        }
        else {
            arynum = 0;
        }
        intIdx = 0;
        intIdex = 0;
        String[] returnStr = new String[arynum];

        if (strSource.compareTo("") != 0) {
            if (strSource.indexOf(strDiv) != -1) {
                intIdx = strSource.indexOf(strDiv);
                returnStr[0] = strSource.substring(0, intIdx);
                for (int intCount = 1; ; intCount++) {
                    if (strSource.indexOf(strDiv, intIdx + div_length) != -1) {
                        intIdex = strSource.indexOf(strDiv, intIdx + div_length);
                        returnStr[intCount] = strSource.substring(intIdx + div_length, intIdex);
                        intIdx = strSource.indexOf(strDiv, intIdx + div_length);
                    } else {
                        returnStr[intCount] = strSource.substring(intIdx + div_length,
                        strSource.length());
                        break;
                    }
                 }
            } else {
                returnStr[0] = strSource.substring(0, strSource.length());
                return returnStr;
            }
        } else {
            return returnStr;
        }
        return returnStr;
    }

    public static String doWithNull(Object o) {
        if(o == null) {
            return "";
        } else {
            String returnValue = o.toString();
            if(returnValue.equalsIgnoreCase("null")) {
                return "";
            } else {
                return returnValue.trim();
            }
        }
    }

    public static String replace(String str, String strSub, String strRpl) {
        String[] tmp = split(str, strSub);
        String returnstr = "";
        if (tmp.length != 0) {
            returnstr = tmp[0];
            for (int i = 0; i < tmp.length - 1; i++) {
                returnstr = doWithNull(returnstr) + strRpl + tmp[i + 1];
            }
        }
        return doWithNull(returnstr);
    }

    public static String convertDate(Date date) {
        String toDate = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return toDate;
    }

    public static String convertTime(Date date) {
        String toTime = new SimpleDateFormat("HH:mm").format(date);
        return toTime;
    }

    public static String convertDateToString(Date date) {
        String toDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        return toDateTime;
    }

    public static String replaceDateFormat(String date) {
        return date.replace("/", "-");
    }

    public static Date convertStringToDate(String date) {
        if (date.equals("")) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dat = sf.parse(replaceDateFormat(date));
            return dat;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
