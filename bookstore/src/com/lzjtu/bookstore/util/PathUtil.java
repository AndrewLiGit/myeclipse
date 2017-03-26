package com.lzjtu.bookstore.util;

import com.lzjtu.bookstore.AppContext;
import com.lzjtu.bookstore.Constants;

public class PathUtil {

	public static String getFullPath(String path) {
        if (path == null) {
            path = "";
        }
        String urlPrefix = Constants.APP_URL_PREFIX;
        if (!StringUtil.isEmpty(urlPrefix)) {
            urlPrefix += "/";
        }
        return AppContext.getContextPath() + "/" + urlPrefix  + path;
    }
}
