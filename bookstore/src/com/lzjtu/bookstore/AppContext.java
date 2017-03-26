package com.lzjtu.bookstore;

import java.util.HashMap;
import java.util.Map;

import com.lzjtu.bookstore.model.User;

public class AppContext {
	
	// private static Map<Thread, AppContext> appContextMap = new HashMap<Thread, AppContext>();
    private static final ThreadLocal<AppContext> appContext = new ThreadLocal<AppContext>();

    private Map<String, Object> values = new HashMap<String, Object>();
    private static String contextPath;

    public static String getContextPath() {
        return contextPath;
    }

    public static void setContextPath(String contextPath) {
        if (AppContext.contextPath == null) {
            AppContext.contextPath = contextPath;
        }
    }

    public Map<String, Object> getObjects() {
        return values;
    }

    public void setObjects(Map<String, Object> objects) {
        if (objects == null) {
            objects = new HashMap<String, Object>();
        }
        this.values = objects;
    }

    public void addObject(String key, Object object) {
        this.values.put(key, object);
    }

    public Object getObject(String key) {
        return values.get(key);
    }

    public void removeObject(String key) {
        values.remove(key);
    }

    public User getUser() {
        return (User)values.get(Constants.APP_CONTEXT_USER);
    }

    public String getUserName() {
        User user = (User) values.get(Constants.APP_CONTEXT_USER);
        if (user != null) {
            return user.getUserName();
        }
        return "";
    }

    public int getUserId() {
        User user = (User) values.get(Constants.APP_CONTEXT_USER);
        if (user != null) {
            return user.getId();
        }
        return 0;
    }

    public void clear() {
        AppContext context = appContext.get();
        if (context != null) {
            context.values.clear();
        }
        context = null;
    }

    private AppContext() {}

    public static AppContext getAppContext() {
        AppContext context = appContext.get();
        if (context == null) {
            context = new AppContext();
            appContext.set(context);
        }
        return context;
    }

}
