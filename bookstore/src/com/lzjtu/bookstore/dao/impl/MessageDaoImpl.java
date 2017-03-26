package com.lzjtu.bookstore.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lzjtu.bookstore.dao.MessageDao;
import com.lzjtu.bookstore.model.Message;
import com.lzjtu.bookstore.model.Pagination;

public class MessageDaoImpl extends SqlSessionDaoSupport implements MessageDao {

	private static final String CLASS_NAME = Message.class.getName();
	
	@Override
	public List<Message> findAllMessage(Pagination pagination) {
		pagination.setTotalCount(this.getCount());
        if (pagination.getCurrentPage() > pagination.getPageCount()){
            pagination.setCurrentPage(pagination.getPageCount());
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("offset", pagination.getOffset());
        params.put("pageSize", pagination.getPageSize());
		
		return getSqlSession().selectList(CLASS_NAME + ".findAllMessage", params);
	}

	@Override
	public int getCount() {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getCount");
	}

	@Override
	public void save(Message message) {
		
		getSqlSession().insert(CLASS_NAME + ".save", message);
	}

	@Override
	public void delete(int id) {
		
		getSqlSession().delete(CLASS_NAME + ".delete", id);
	}

}
