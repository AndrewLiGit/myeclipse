package com.lzjtu.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lzjtu.bookstore.Constants;
import com.lzjtu.bookstore.model.Notice;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.service.NoticeService;

@Controller
@RequestMapping(Constants.APP_URL_NOTICE_PREFIX)
public class NoticeController extends BaseController {
	
	private static final String NOTICE_DETAIL_JSP = "noticeDetail";
	private static final String NOTICE_LIST_JSP = "admin/noticeList";
	private static final String NOTICE_SHOW_JSP = "admin/noticeShow";
	private static final String NOTICE_MODIFY_JSP = "admin/noticeModify";
	private static final String NOTICE_LIST_PAGE = "notice/list";
	
	@Autowired
	private NoticeService noticeService;

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@RequestMapping(value="/getNoticeById", method = RequestMethod.GET)
	public ModelAndView getNoticeById (int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		Notice notice = noticeService.getNoticeById(id);
		
		modelAndView.addObject("notice", notice);
		
		modelAndView.setViewName(NOTICE_DETAIL_JSP);
		
		return modelAndView;
	}
	
	//后台
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
			
			List<Notice> list = noticeService.list(pagination);
			
			modelAndView.addObject("list", list);
			modelAndView.addObject("pagination", pagination);
			
			modelAndView.setViewName(NOTICE_LIST_JSP);
			
			return modelAndView;
		}
		
		@RequestMapping(value="/getById", method = RequestMethod.GET)
		public ModelAndView getById (int id) {
			ModelAndView modelAndView = new ModelAndView();
			
			Notice notice = noticeService.getNewsById(id);
			
			modelAndView.addObject("notice", notice);
			
			modelAndView.setViewName(NOTICE_SHOW_JSP);
			
			return modelAndView;
		}
		
		@RequestMapping(value="/modifyById", method = RequestMethod.GET)
		public ModelAndView modifyById (int id) {
			ModelAndView modelAndView = new ModelAndView();
			
			Notice notice = noticeService.getNewsById(id);
			
			modelAndView.addObject("notice", notice);
			
			modelAndView.setViewName(NOTICE_MODIFY_JSP);
			
			return modelAndView;
		}
		
		@RequestMapping(value="/create", method = RequestMethod.GET)
		public ModelAndView create () {
			ModelAndView modelAndView = new ModelAndView();
			
			Notice notice = new Notice();
			
			modelAndView.addObject("notice", notice);
			
			modelAndView.setViewName(NOTICE_MODIFY_JSP);
			
			return modelAndView;
		}
		
		@RequestMapping(value="/save", method = RequestMethod.POST)
		public ModelAndView save(Notice notice) {
			
			ModelAndView modelAndView = new ModelAndView();
			
			noticeService.save(notice);
			
			this.addSession("msg", "保存公告成功。");
			
			modelAndView.setView(this.getRedirectView(NOTICE_LIST_PAGE));
			
			return modelAndView;
		}
		
		@RequestMapping(value="/delete", method = RequestMethod.GET)
		public ModelAndView delete(int id) {
			
			ModelAndView modelAndView = new ModelAndView();
			
			noticeService.delete(id);
			
			this.addSession("msg", "删除公告成功。");
			
			modelAndView.setView(this.getRedirectView(NOTICE_LIST_PAGE));
			
			return modelAndView;
		}
}
