package com.briup.dao;

import com.briup.bean.Student;

public interface IStudentDao {
	
	void save(Student student) throws Exception;
	Student findStudentByName(String name) throws Exception;
	
}
