package com.demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.entity.User;
import com.mysql.cj.Query;

public class UserDao {

	private SessionFactory factory=null;
	private Session session=null;
	private Transaction tx=null;
	
	public UserDao(SessionFactory factory) {
		this.factory = factory;
	}
	
	public boolean saveuser(User user) {
		boolean f=false;
		
		try {
		
			session = factory.openSession();
			tx = session.beginTransaction();
			
			session.save(user);
			tx.commit();
			f=true;
			
		} catch (Exception e) {
			// TODO: handle exception
			if(tx!=null) {
				f = false;
				e.printStackTrace();
			}
		}
		
		return f;
	}
	
	public User login(String email,String password) {
		User u=null;
		
		session = factory.openSession();
		org.hibernate.query.Query query=  session.createQuery("from User where email=:em and password=:ps");
		
		query.setParameter("em", email);
		query.setParameter("ps", password);
		
		u = (User) query.uniqueResult();
		return u;
	}
}
