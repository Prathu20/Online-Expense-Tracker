package com.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.demo.entity.Expense;
import com.demo.entity.User;
import com.mysql.cj.Query;

public class ExpenseDao {

	private SessionFactory factory = null;
	private Session session = null;
	private Transaction tx = null;

	public ExpenseDao(SessionFactory factory) {
		this.factory = factory;
	}

	public boolean saveExpense(Expense exp) {
		boolean f = false;

		try {

			session = factory.openSession();

			tx = session.beginTransaction();

			session.save(exp);
			tx.commit();
			f = true;

		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				f = false;
				e.printStackTrace();
			}
		}

		return f;
	}

	public List<Expense> getAllExpenseByUser(User user) {

		List<Expense> list = new ArrayList<Expense>();
		try {

			session = factory.openSession();
			org.hibernate.Query<Expense> query = session.createQuery("from Expense where user=:us");
			query.setParameter("us", user);
			list = query.list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}

	public Expense getExpenseById(int id) {

		Expense ex = null;

		try {
			session = factory.openSession();
			org.hibernate.Query<Expense> query = session.createQuery("from Expense where id=:id");
			query.setParameter("id", id);
			ex = (Expense) query.uniqueResult();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ex;
	}

	public boolean updateExpense(Expense exp) {
		boolean f = false;

		try {

			session = factory.openSession();

			tx = session.beginTransaction();

			session.saveOrUpdate(exp);
			tx.commit();
			f = true;

		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null) {
				f = false;
				e.printStackTrace();
			}
		}

		return f;
	}

	public boolean deleteExpense(int id) {
		boolean f = false;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();

			Expense ex = session.get(Expense.class, id);
			session.delete(ex);
			tx.commit();
			f = true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return f;
	}
}
