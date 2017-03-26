package com.lzjtu.bookstore.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.lzjtu.bookstore.AppContext;
import com.lzjtu.bookstore.controller.BaseController;
import com.lzjtu.bookstore.model.User;
import com.lzjtu.bookstore.util.PathUtil;
import com.lzjtu.bookstore.util.SessionUtil;

public class BaseController {

	private final Logger logger = Logger.getLogger(BaseController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception e) {
        logger.error(e.getMessage(), e);
        ModelAndView modelAndview = new ModelAndView(new RedirectView(AppContext.getContextPath() + "/static/500.html"));
        return modelAndview;
    }

    protected User getUser() {
        return AppContext.getAppContext().getUser();
    }

    public String getUserName() {
        User user = getUser();
        if (user !=null) {
            return user.getUserName();
        }
        return "";
    }

    public int getUserId() {
        User user = getUser();
        if (user !=null) {
            return user.getId();
        }
        return 0;
    }

    protected RedirectView getRedirectView(String path) {
        return new RedirectView(PathUtil.getFullPath(path));
    }

    protected void addSession(String key, Object object) {
        SessionUtil.addSession(key, object);
    }

    protected Object getSession(String key) {
    	Object object = SessionUtil.getSession(key);
    	
    	return object;
    }

    protected void removeSession(String key) {
        SessionUtil.removeSession(key);
    }

    protected void invalidate() {
        SessionUtil.invalidate();
    }

}
