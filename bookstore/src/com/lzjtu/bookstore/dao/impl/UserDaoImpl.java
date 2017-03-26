package com.lzjtu.bookstore.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.lzjtu.bookstore.dao.UserDao;
import com.lzjtu.bookstore.model.Pagination;
import com.lzjtu.bookstore.model.User;

public class UserDaoImpl extends SqlSessionDaoSupport implements UserDao {

	private static final String CLASS_NAME = User.class.getName();
	
	@Override
	public User getUserByUserName(String userName) {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getUserByUserName", userName);
	}

	@Override
	public int save(User user) {
		
		return getSqlSession().insert(CLASS_NAME + ".save", user);
	}

	@Override
	public List<User> list(Pagination pagination) {
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
	public int getCount() {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getCount");
	}

	@Override
	public int getCountByName(String userName) {
		
		return getSqlSession().selectOne(CLASS_NAME + ".getCountByName", userName);
	}

}
