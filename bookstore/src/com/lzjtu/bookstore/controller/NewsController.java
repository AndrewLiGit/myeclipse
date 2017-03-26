package com.lzjtu.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lzjtu.bookstore.Constants;
import com.lzjtu.bookstore.model.News;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.service.NewsService;

@Controller
@RequestMapping(Constants.APP_URL_NEWS_PREFIX)
public class NewsController extends BaseController {

	private static final String NEWS_DETAIL_JSP = "newsDetail";
	private static final String NEWS_LIST_JSP = "admin/newsList";
	private static final String NEWS_SHOW_JSP = "admin/newsShow";
	private static final String NEWS_MODIFY_JSP = "admin/newsModify";
	private static final String NEWS_LIST_PAGE = "news/list";
	
	@Autowired
	private NewsService newsService;

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	@RequestMapping(value="/getNewsById", method = RequestMethod.GET)
	public ModelAndView getNewsById (int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		News news = newsService.getNewsById(id);
		
		modelAndView.addObject("news", news);
		
		modelAndView.setViewName(NEWS_DETAIL_JSP);
		
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
		
		List<News> list = newsService.list(pagination);
		
		modelAndView.addObject("list", list);
		modelAndView.addObject("pagination", pagination);
		
		modelAndView.setViewName(NEWS_LIST_JSP);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/getById", method = RequestMethod.GET)
	public ModelAndView getById (int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		News news = newsService.getNewsById(id);
		
		modelAndView.addObject("news", news);
		
		modelAndView.setViewName(NEWS_SHOW_JSP);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/modifyById", method = RequestMethod.GET)
	public ModelAndView modifyById (int id) {
		ModelAndView modelAndView = new ModelAndView();
		
		News news = newsService.getNewsById(id);
		
		modelAndView.addObject("news", news);
		
		modelAndView.setViewName(NEWS_MODIFY_JSP);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create () {
		ModelAndView modelAndView = new ModelAndView();
		
		News news = new News();
		
		modelAndView.addObject("news", news);
		
		modelAndView.setViewName(NEWS_MODIFY_JSP);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ModelAndView save(News news) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		newsService.save(news);
		
		this.addSession("msg", "保存新闻成功。");
		
		modelAndView.setView(this.getRedirectView(NEWS_LIST_PAGE));
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.GET)
	public ModelAndView delete(int id) {
		
		ModelAndView modelAndView = new ModelAndView();
		
		newsService.delete(id);
		
		this.addSession("msg", "删除新闻成功。");
		
		modelAndView.setView(this.getRedirectView(NEWS_LIST_PAGE));
		
		return modelAndView;
	}
	
}
