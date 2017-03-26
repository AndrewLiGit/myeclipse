package com.lzjtu.bookstore.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lzjtu.bookstore.dao.NoticeDao;
import com.lzjtu.bookstore.model.Notice;
import com.lzjtu.bookstore.model.Pagination;

public class NoticeDaoImpl extends SqlSessionDaoSupport implements NoticeDao {

	private static final String CLASS_NAME = Notice.class.getName();
	
	@Override
	public List<Notice> findNotice() {
		
		return getSqlSession().selectList(CLASS_NAME + ".findNotice");
	}

	@Override
	public Notice getNoticeById(int id) {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getNoticeById", id);
	}

	@Override
	public List<Notice> list(Pagination pagination) {
		
		pagination.setTotalCount(this.getCount());
		if (pagination.getCurrentPage() > pagination.getPageCount()){
            pagination.setCurrentPage(pagination.getPageCount());
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", pagination.getOffset());
        params.put("pageSize", pagination.getPageSize());
		
		return getSqlSession().selectList(CLASS_NAME + ".list", params);
	}

	@Override
	public void update(Notice notice) {
		
		getSqlSession().update(CLASS_NAME + ".update", notice);
	}

	@Override
	public void create(Notice notice) {
		
		getSqlSession().insert(CLASS_NAME + ".create", notice);
	}

	@Override
	public void delete(int id) {
		
		getSqlSession().delete(CLASS_NAME + ".delete", id);
	}

	@Override
	public int getCount() {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getCount");
	}

}
