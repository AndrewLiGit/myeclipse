package com.briup.common.transaction;

import org.hibernate.Session;

import com.briup.common.util.HibernateSessionFactory;

public class HibernateTransaction implements Transaction {
	private org.hibernate.Transaction transaction;

	public void beginTransaction() {
		Session session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		
	}

	public void commit() {
		transaction.commit();
	}

	public void rollback() {
		transaction.rollback();
	}
}
