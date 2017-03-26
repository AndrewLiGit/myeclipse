package com.lzjtu.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lzjtu.bookstore.Constants;
import com.lzjtu.bookstore.model.Message;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.service.MessageService;

@Controller
@RequestMapping(Constants.APP_URL_MESSAGE_PREFIX)
public class MessageController extends BaseController {

	private static final String MESSAGE_JSP = "message";
	private static final String LIST_PAGE = "message/list";
	private static final String MESSAGE_ADMIN_JSP = "admin/messageList";
	private static final String LIST_MESSAGE_ADMIN_PAGE = "message/listMess";
	
	@Autowired
	private MessageService messageService;

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@RequestMapping(value="list", method = RequestMethod.GET)
	public ModelAndView list(
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			) {
		if (currentPage < 1) {
            currentPage = 1;
        }
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(currentPage);
		ModelAndView modelAndView = new ModelAndView();
		
		List<Message> list = messageService.findAllMessage(pagination);
		
		modelAndView.addObject("list", list);
		
		modelAndView.addObject("pagination", pagination);
		
		modelAndView.setViewName(MESSAGE_JSP);
		
		return modelAndView;
	}
	
	@RequestMapping(value="save", method = RequestMethod.GET)
	public ModelAndView save(
			@RequestParam(value = "content", defaultValue = "") String content
			) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String userName = this.getUserName();
		
		Message message = new Message();
		
		message.setContent(content);
		message.setUserName(userName);
		
		messageService.save(message);
		
		modelAndView.setView(this.getRedirectView(LIST_PAGE));
		
		return modelAndView;
	}
	
	//后台
	@RequestMapping(value="listMess", method = RequestMethod.GET)
	public ModelAndView listMess(
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage
			) {
		if (currentPage < 1) {
            currentPage = 1;
        }
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(currentPage);
		ModelAndView modelAndView = new ModelAndView();
		
		List<Message> list = messageService.findAllMessage(pagination);
		
		modelAndView.addObject("list", list);
		
		modelAndView.addObject("pagination", pagination);
		
		modelAndView.setViewName(MESSAGE_ADMIN_JSP);
		
		return modelAndView;
	}
	
	@RequestMapping(value="saveMess", method = RequestMethod.GET)
	public ModelAndView saveMess(
			@RequestParam(value = "content", defaultValue = "") String content
			) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		String userName = this.getUserName();
		
		Message message = new Message();
		
		message.setContent(content);
		message.setUserName(userName);
		
		messageService.save(message);
		this.addSession("msg", "留言回复成功。");
		modelAndView.setView(this.getRedirectView(LIST_MESSAGE_ADMIN_PAGE));
		
		return modelAndView;
	}
	
	@RequestMapping(value="delete", method = RequestMethod.GET)
	public ModelAndView delete(int id) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		messageService.delete(id);
		this.addSession("msg", "留言删除成功。");
		modelAndView.setView(this.getRedirectView(LIST_MESSAGE_ADMIN_PAGE));
		
		return modelAndView;
	}
	
}
