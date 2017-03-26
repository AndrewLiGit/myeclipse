package com.lzjtu.bookstore.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lzjtu.bookstore.Constants;
import com.lzjtu.bookstore.model.Book;
import com.lzjtu.bookstore.model.BookOrder;
import com.lzjtu.bookstore.model.Order;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.model.ShopCart;
import com.lzjtu.bookstore.service.BookOrderService;
import com.lzjtu.bookstore.service.BookService;
import com.lzjtu.bookstore.service.OrderService;

@Controller
@RequestMapping(Constants.APP_URL_ORDER_PREFIX)
public class OrderController extends BaseController{
	
	private static final String INDEX_PAGE = "book/index";
	private static final String ORDER_LIST_JSP = "admin/orderList";
	private static final String ORDER_DETAIL_JSP = "admin/orderDetail";
	private static final String ORDER_LIST_BY_USER_ID_JSP = "orderListByUserId";
	private static final String ORDER_BOOK_DETAIL_JSP = "orderBookDetail";
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BookOrderService bookOrderService;
	
	@Autowired
	private BookService bookService;

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}

	public void setBookOrderService(BookOrderService bookOrderService) {
		this.bookOrderService = bookOrderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView save(int payStyleId) throws Exception {
        int userId = this.getUserId();
        
        ShopCart shopCart = (ShopCart)this.getSession(Constants.SHOP_CART);
        Order order = new Order();
        
        int r1 = (int)(Math.random()*(10));
        int r2 = (int)(Math.random()*(10));
        long orderNo = System.currentTimeMillis();
        
        order.setPayStyleId(payStyleId);
        order.setUserId(userId);
        order.setCost(shopCart.getTotalPrice().floatValue());
        order.setOrderNo(orderNo + String.valueOf(r1) + String.valueOf(r2));
        
        int orderId = orderService.save(order, userId);
        
        Iterator<BookOrder > iter = shopCart.getBookOrders();
        
        while(iter.hasNext()) {
        	BookOrder bookOrder = iter.next();
        	bookOrder.setOrderId(orderId);
        	bookOrder.setBookId(bookOrder.getBook().getId());
        	bookOrderService.save(bookOrder);
        }
        
        shopCart.removeAllBooks();
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(this.getRedirectView(INDEX_PAGE));
        
        return modelAndView;
    }
	
	@RequestMapping(value = "/listByUserId", method = RequestMethod.GET)
	public ModelAndView listByUserId(@RequestParam(value = "currentPage", defaultValue = "1") int currentPage) {
    	
		if (currentPage < 1) {
            currentPage = 1;
        }
		
		int userId = this.getUserId();
		
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(currentPage);
        ModelAndView modelAndView = new ModelAndView();
        List<Order> list = new ArrayList<Order>();
        list = orderService.listByUserId(pagination, userId);

        modelAndView.addObject("pagination", pagination);
        modelAndView.addObject("List", list);
        modelAndView.setViewName(ORDER_LIST_BY_USER_ID_JSP);
        
        return modelAndView;
	}
	
	@RequestMapping(value = "/getOrderBookDetail", method = RequestMethod.GET)
	public ModelAndView getOrderBookDetail(int orderId) {
    	
        ModelAndView modelAndView = new ModelAndView();
        
        Order order = orderService.getById(orderId);
        
        List<BookOrder> list = new ArrayList<BookOrder>();
        list = bookOrderService.getBookOrder(orderId);
        Map<Integer, Book> map = new HashMap<Integer, Book>();
        
        for(int i = 0; i < list.size(); i ++) {
        	Book book = bookService.getBookById(list.get(i).getBookId());
        	map.put(book.getId(), book);
        }
        
        modelAndView.addObject("order", order);
        modelAndView.addObject("map", map);
        modelAndView.addObject("List", list);
        modelAndView.setViewName(ORDER_BOOK_DETAIL_JSP);
        
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
        List<Order> list = new ArrayList<Order>();
        list = orderService.list(pagination);

        modelAndView.addObject("pagination", pagination);
        modelAndView.addObject("List", list);
        modelAndView.setViewName(ORDER_LIST_JSP);
        
        return modelAndView;
	}
	
	@RequestMapping(value = "/getOrderDetail", method = RequestMethod.GET)
	public ModelAndView getOrderDetail(int orderId) {
    	
        ModelAndView modelAndView = new ModelAndView();
        
        Order order = orderService.getById(orderId);
        
        List<BookOrder> list = new ArrayList<BookOrder>();
        list = bookOrderService.getBookOrder(orderId);
        Map<Integer, Book> map = new HashMap<Integer, Book>();
        
        for(int i = 0; i < list.size(); i ++) {
        	Book book = bookService.getBookById(list.get(i).getBookId());
        	map.put(book.getId(), book);
        }
        
        modelAndView.addObject("order", order);
        modelAndView.addObject("map", map);
        modelAndView.addObject("List", list);
        modelAndView.setViewName(ORDER_DETAIL_JSP);
        
        return modelAndView;
	}

}
