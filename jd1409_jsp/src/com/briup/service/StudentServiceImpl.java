package com.briup.service;

import com.briup.bean.Student;
import com.briup.dao.IStudentDao;
import com.briup.dao.StudentDaoImpl;

//IStudentService
public class StudentServiceImpl implements IStudentService{
	private IStudentDao dao = new StudentDaoImpl();
	@Override
	public void register(Student student) throws Exception {
		
		Student s = dao.findStudentByName(student.getName());
		
		if(s!=null){
			throw new Exception("用户名已经被占用");
		}
		
		dao.save(student);
		
	}

}
