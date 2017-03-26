package com.lzjtu.bookstore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lzjtu.bookstore.AppContext;
import com.lzjtu.bookstore.Constants;
import com.lzjtu.bookstore.controller.BaseController;
import com.lzjtu.bookstore.exception.ParameterException;
import com.lzjtu.bookstore.exception.ServiceException;
import com.lzjtu.bookstore.model.ContactInfo;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.model.ShopCart;
import com.lzjtu.bookstore.model.User;
import com.lzjtu.bookstore.service.UserService;
import com.lzjtu.bookstore.util.StringUtil;

@Controller
@RequestMapping(Constants.APP_URL_USER_PREFIX)
public class UserController extends BaseController {

	private final String LOGIN_JSP = "login";
	private final String REGISTER_JSP = "register";
    private final String INDEX_PAGE = "book/index";
    private final String ADMIN_INDEX_PAGE = "user/index";
    private final String INDEX_ADMIN_JSP = "admin/indexAdmin";
    private final String USER_LIST_JSP = "admin/userList";
    private final String USER_DETAIL_JSP = "admin/userDetail";
    
    
	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "go", defaultValue = "") String go) {

        User user = this.getUser();
        ModelAndView modelAndView = new ModelAndView();

        if (user !=null) {
        	if(user.getStatus() == 1) {
		        
		        modelAndView.setView(this.getRedirectView(INDEX_PAGE));
	        }
	        
	        if(user.getStatus() == 0) {
		        
		        modelAndView.setView(this.getRedirectView(ADMIN_INDEX_PAGE));
	        }
        } else {
        	modelAndView.addObject("go", go);
        	modelAndView.setViewName(LOGIN_JSP);
        }

        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView saveLogin(
                                   @RequestParam(value = "userName", defaultValue = "") String userName,
                                   @RequestParam(value = "password", defaultValue = "") String password,
                                   @RequestParam(value = "queryString", defaultValue = "") String queryString,
                                   @RequestParam(value = "go", defaultValue = "") String go
                                   ) {
        ModelAndView modelAndView = new ModelAndView();
        try {
	        User user = null;
	        user = userService.login(userName, password);
	        user.setPassword(null);

	        this.addSession(Constants.USER, user);
	        
	        if (!StringUtil.isEmpty(queryString)) {
	            if (queryString.startsWith("#")) {
	                queryString = queryString.substring(1);
	            }
	            go = go + "?" + queryString;
	        }
	        
	        if(user.getStatus() == 1) {
		        ShopCart shopCart = new ShopCart();
		        
		        this.addSession(Constants.SHOP_CART, shopCart);
		        
		        String uri = StringUtil.isEmpty(go) ? INDEX_PAGE : go;
		        modelAndView.setView(this.getRedirectView(uri));
	        }
	        
	        if(user.getStatus() == 0) {
		        
		        String uri = StringUtil.isEmpty(go) ? ADMIN_INDEX_PAGE : go;
		        modelAndView.setView(this.getRedirectView(uri));
	        }
	        
        } catch (ParameterException parameterException) {
            Map<String, String> errorFields = parameterException.getErrorFields();
            modelAndView.addObject(Constants.ERROR_FIELDS, errorFields);
            modelAndView.setViewName(LOGIN_JSP);
        } catch (ServiceException serviceException) {
            modelAndView.addObject(Constants.TIP_MESSAGE, serviceException.getMessage());
            modelAndView.setViewName(LOGIN_JSP);
        }
            
        return modelAndView;
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        User user = this.getUser();
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
        	modelAndView.setView(this.getRedirectView(INDEX_PAGE));
            return modelAndView;
        }
        this.removeSession(Constants.USER);
        
        modelAndView.setView(this.getRedirectView(INDEX_PAGE));
        return modelAndView;
    }
    
    @RequestMapping(value="/register", method = RequestMethod.GET)
    public ModelAndView create(){
    	ModelAndView modelAndView = new ModelAndView();
    	
    	modelAndView.setViewName(REGISTER_JSP);
    	
    	return modelAndView;
    }
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ModelAndView save(User user, ContactInfo contactInfo){
    	ModelAndView modelAndView = new ModelAndView();
    	userService.save(user, contactInfo);
    	
    	this.addSession(Constants.USER, user);
    	ShopCart shopCart = new ShopCart();
        this.addSession(Constants.SHOP_CART, shopCart);
        
    	this.addSession(Constants.SAVE_USER_SUCESSFULLY, "注册用户成功。");
    	modelAndView.setView(this.getRedirectView(INDEX_PAGE));
    	
    	return modelAndView;
    }
    
    //后台
    @RequestMapping(value="/index", method = RequestMethod.GET)
    public ModelAndView index(@RequestParam(value = "go", defaultValue = "") String go){
    	User user = this.getUser();
        ModelAndView modelAndView = new ModelAndView();

        if (user !=null) {
        	if(user.getStatus() == 1) {
		        
		        modelAndView.setView(this.getRedirectView(INDEX_PAGE));
	        }
	        
	        if(user.getStatus() == 0) {
		        
	        	modelAndView.setViewName(INDEX_ADMIN_JSP);
	        }
        } else {
        	modelAndView.addObject("go", go);
        	modelAndView.setViewName(LOGIN_JSP);
        }
    	
    	return modelAndView;
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
    	
		if (currentPage < 1) {
            currentPage = 1;
        }
		
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(currentPage);
        ModelAndView modelAndView = new ModelAndView();
        List<User> list = new ArrayList<User>();
        list = userService.list(pagination);

        modelAndView.addObject("pagination", pagination);
        modelAndView.addObject("List", list);
        modelAndView.setViewName(USER_LIST_JSP);
        
        return modelAndView;
	}
    
    @RequestMapping(value = "/getUserDetail", method = RequestMethod.GET)
	public ModelAndView getUserDetail(String userName) {
    	
        ModelAndView modelAndView = new ModelAndView();
        ContactInfo contactInfo = userService.getContactInfoByName(userName);
        User user = userService.getUser(userName);
        
        modelAndView.addObject("contactInfo", contactInfo);
        modelAndView.addObject("users", user);
        modelAndView.setViewName(USER_DETAIL_JSP);
        
        return modelAndView;
	}
    
    /*@RequestMapping(value = "/checkUserName", method = RequestMethod.GET)
	public void checkUserName(String userName) throws IOException {
    	
        int count = 0;
        count = userService.getCountByName(userName);
        System.out.println("+++++++++" + count);
        AppContext appContext = AppContext.getAppContext();
        HttpServletResponse response = (HttpServletResponse) appContext.getObject(Constants.APP_CONTEXT_RESPONSE);
        //response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        System.out.println("+++++++++" + count);
        out.write(count);
	}*/
    
    @RequestMapping(value = "/checkUserName", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	Map<String, Object> map = new HashMap<String, Object>();
        int count = 0;
        count = userService.getCountByName(request.getParameter("userName"));
        System.out.println("+++++++++" + count);
        if(count > 0) {
        	map.put("msg", "此用户名已经存在。");
        }
        /*AppContext appContext = AppContext.getAppContext();
        HttpServletResponse response = (HttpServletResponse) appContext.getObject(Constants.APP_CONTEXT_RESPONSE);
        //response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        System.out.println("+++++++++" + count);
        out.write(count);*/
		return map;
	}
}
