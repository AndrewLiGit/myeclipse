package com.lzjtu.bookstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lzjtu.bookstore.Constants;
import com.lzjtu.bookstore.model.BigCategory;
import com.lzjtu.bookstore.model.Book;
import com.lzjtu.bookstore.model.ContactInfo;
import com.lzjtu.bookstore.model.News;
import com.lzjtu.bookstore.model.Notice;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.model.PayWay;
import com.lzjtu.bookstore.model.ShopCart;
import com.lzjtu.bookstore.service.BigCategoryService;
import com.lzjtu.bookstore.service.BookService;
import com.lzjtu.bookstore.service.NewsService;
import com.lzjtu.bookstore.service.NoticeService;
import com.lzjtu.bookstore.service.PayWayService;
import com.lzjtu.bookstore.service.UserService;

@Controller
@RequestMapping(Constants.APP_URL_BOOK_PREFIX)
public class BookController extends BaseController{

	private static final String INDEX_JSP = "index";
	private static final String SEARCH_JSP = "searchBooks";
	private static final String SEARCH_BY_CATEGORY_JSP = "searchByCategory";
	private static final String BOOK_DETAIL_JSP = "bookDetail";
	private static final String SHOP_CART_JSP = "shopCart";
	private static final String INDEX_PAGE = "book/index";
	private static final String CONFIRM_ORDER_JSP = "confirmOrder";
	private static final String BOOK_LIST_JSP = "admin/bookList";
	private static final String BOOK_SHOW_JSP = "admin/bookShow";
	private static final String BOOK_LIST_PAGE = "book/list";
	private static final String BOOK_MODIFY_JSP = "admin/bookModify";
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private NewsService newsService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PayWayService payWayService;
	
	@Autowired
	private BigCategoryService bigCategoryService;

	public void setBigCategoryService(BigCategoryService bigCategoryService) {
		this.bigCategoryService = bigCategoryService;
	}

	public void setPayWayService(PayWayService payWayService) {
		this.payWayService = payWayService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public BookService getBookService() {
		return bookService;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public NoticeService getNoticeService() {
		return noticeService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		
		ModelAndView modelAndView = new ModelAndView();
		
		List<Book> 	hotList = new ArrayList<Book>();
		List<Book> specialList = new ArrayList<Book>();
		List<News> newsList = new ArrayList<News>();
		List<Notice> noticeList = new ArrayList<Notice>();
		
		hotList = bookService.findHot();
		specialList = bookService.findSpecial();
		newsList = newsService.findNews();
		noticeList = noticeService.findNotice();
		
		modelAndView.addObject("hotList", hotList);
		modelAndView.addObject("specialList", specialList);
		modelAndView.addObject("newsList", newsList);
		modelAndView.addObject("noticeList", noticeList);
		
		modelAndView.setViewName(INDEX_JSP);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchByKeyword(
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "keyword", defaultValue = "") String keyword
			){
		if (currentPage < 1) {
            currentPage = 1;
        }
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(currentPage);
        ModelAndView modelAndView = new ModelAndView();
        List<Book> list = new ArrayList<Book>();
        list = bookService.findByKeyword(pagination, keyword);

        modelAndView.addObject("keyword", keyword);
        modelAndView.addObject("pagination", pagination);
        modelAndView.addObject("List", list);
        modelAndView.setViewName(SEARCH_JSP);
        
        return modelAndView;
	}
	
	@RequestMapping(value = "/searchCategory", method = RequestMethod.GET)
	public ModelAndView searchByCategory(
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
            @RequestParam(value = "category", defaultValue = "") String category
			){
		if (currentPage < 1) {
            currentPage = 1;
        }
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(currentPage);
        ModelAndView modelAndView = new ModelAndView();
        List<Book> list = new ArrayList<Book>();
        list = bookService.findByCategory(pagination, category);

        modelAndView.addObject("keyword", category);
        modelAndView.addObject("pagination", pagination);
        modelAndView.addObject("List", list);
        modelAndView.setViewName(SEARCH_BY_CATEGORY_JSP);
        
        return modelAndView;
	}
	
	@RequestMapping(value = "/bookDetailById", method = RequestMethod.GET)
	public ModelAndView bookDetailById(
			@RequestParam(value = "id", defaultValue = "") int id
			){
		
        ModelAndView modelAndView = new ModelAndView();
        Book book = bookService.getBookById(id);
        
        modelAndView.addObject("book", book);
        modelAndView.setViewName(BOOK_DETAIL_JSP);
        
        return modelAndView;
	}
	
	@RequestMapping("/addBookToShopCart")
	public ModelAndView addBookToShopCart(String id){
        ModelAndView modelAndView = new ModelAndView();
        ShopCart shopCart = (ShopCart) this.getSession(Constants.SHOP_CART);
        Book book = bookService.getBookById(Integer.valueOf(id));
        
        try {
			shopCart.addBook(book);
			this.addSession(Constants.SHOP_CART, shopCart);
			modelAndView.addObject("msg", "图书添加成功。");
			modelAndView.addObject("book", book);
			
			modelAndView.setViewName(BOOK_DETAIL_JSP);
	        
		} catch (Exception e) {
			modelAndView.addObject("msg", "图书添加失败，请重新添加。");
			modelAndView.addObject("book", book);
			
			modelAndView.setViewName(BOOK_DETAIL_JSP);
			e.printStackTrace();
		}

        return modelAndView;
	}
	
	@RequestMapping("/showShopCart")
	public ModelAndView showShopCart(){
        ModelAndView modelAndView = new ModelAndView();
	    
		modelAndView.setViewName(SHOP_CART_JSP);

        return modelAndView;
	}
	
	@RequestMapping("/deleteBookOrder")
	public ModelAndView deleteBookOrder(int id){
        ModelAndView modelAndView = new ModelAndView();
	    
        ShopCart shopCart = (ShopCart) this.getSession(Constants.SHOP_CART);
        try {
			shopCart.removeBook(id);
			this.addSession("msg", "图书删除成功。");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.setViewName(SHOP_CART_JSP);

        return modelAndView;
	}
	
	@RequestMapping("/updateBookOrder")
	public ModelAndView updateBookOrder(int id, int number){
        ModelAndView modelAndView = new ModelAndView();
	    
        ShopCart shopCart = (ShopCart) this.getSession(Constants.SHOP_CART);
        try {
			shopCart.updateBook(id, number);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.setViewName(SHOP_CART_JSP);

        return modelAndView;
	}
	
	@RequestMapping("/removeAllBookOrder")
	public ModelAndView removeAllBookOrder(){
        ModelAndView modelAndView = new ModelAndView();
	    
        ShopCart shopCart = (ShopCart) this.getSession(Constants.SHOP_CART);
        try {
			shopCart.removeAllBooks();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		modelAndView.setView(this.getRedirectView(INDEX_PAGE));

        return modelAndView;
	}
	
	@RequestMapping("/confirmOrder")
	public ModelAndView confirmOrder(){
        ModelAndView modelAndView = new ModelAndView();
	    
        String userName = this.getUserName();
        
        List<PayWay> payWayList = payWayService.findAllPayWay();
        ContactInfo contactInfo = userService.getContactInfoByName(userName);
        
        modelAndView.addObject("payWayList", payWayList);
        modelAndView.addObject("contactInfo", contactInfo);
        
		modelAndView.setViewName(CONFIRM_ORDER_JSP);

        return modelAndView;
	}
	
	//立即购买
	@RequestMapping("/nowBuy")
	public ModelAndView nowBuy(String id){
        ModelAndView modelAndView = new ModelAndView();
        
        ShopCart shopCart = (ShopCart) this.getSession(Constants.SHOP_CART);
        Book book = bookService.getBookById(Integer.valueOf(id));
        
        try {
			shopCart.addBook(book);
			this.addSession(Constants.SHOP_CART, shopCart);
//			modelAndView.addObject("book", book);
//			
//			modelAndView.setViewName(BOOK_DETAIL_JSP);
	        
		} catch (Exception e) {
			modelAndView.addObject("msg", "图书添加失败，请重新添加。");
			modelAndView.addObject("book", book);
			
			modelAndView.setViewName(BOOK_DETAIL_JSP);
			e.printStackTrace();
		}
	    
        String userName = this.getUserName();
        
        List<PayWay> payWayList = payWayService.findAllPayWay();
        ContactInfo contactInfo = userService.getContactInfoByName(userName);
        
        modelAndView.addObject("payWayList", payWayList);
        modelAndView.addObject("contactInfo", contactInfo);
        
		modelAndView.setViewName(CONFIRM_ORDER_JSP);

        return modelAndView;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public ModelAndView list (
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			) {

		if (currentPage < 1) {
            currentPage = 1;
        }
		
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(currentPage);
        
		ModelAndView modelAndView = new ModelAndView();
		
		List<Book> list = bookService.list(pagination);
		
		modelAndView.addObject("list", list);
		modelAndView.addObject("pagination", pagination);
		
		modelAndView.setViewName(BOOK_LIST_JSP);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/bookShow", method = RequestMethod.GET)
	public ModelAndView bookShow(
			@RequestParam(value = "id", defaultValue = "") int id
			){
		
        ModelAndView modelAndView = new ModelAndView();
        Book book = bookService.getBookById(id);
        
        modelAndView.addObject("book", book);
        modelAndView.setViewName(BOOK_SHOW_JSP);
        
        return modelAndView;
	}
	
	@RequestMapping(value = "/deleteById", method = RequestMethod.GET)
	public ModelAndView deleteById(int id) {
		
        ModelAndView modelAndView = new ModelAndView();
        bookService.deleteById(id);
        
        this.addSession("msg", "图书删除成功。");
        modelAndView.setView(this.getRedirectView(BOOK_LIST_PAGE));
        
        return modelAndView;
	}
	
	@RequestMapping(value="/modifyById", method = RequestMethod.GET)
	public ModelAndView modifyById (int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		Book book = bookService.getBookById(id);
		List<BigCategory> list = bigCategoryService.list();
		
		modelAndView.addObject("book", book);
		modelAndView.addObject("list", list);
		
		modelAndView.setViewName(BOOK_MODIFY_JSP);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create () {
		ModelAndView modelAndView = new ModelAndView();
		
		Book book = new Book();
		
		List<BigCategory> list = bigCategoryService.list();
		
		modelAndView.addObject("book", book);
		modelAndView.addObject("list", list);
		
		modelAndView.setViewName(BOOK_MODIFY_JSP);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ModelAndView save(Book book) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		bookService.save(book);
		
		this.addSession("msg", "保存图书成功。");
		
		modelAndView.setView(this.getRedirectView(BOOK_LIST_PAGE));
		
		return modelAndView;
	}
	
}
