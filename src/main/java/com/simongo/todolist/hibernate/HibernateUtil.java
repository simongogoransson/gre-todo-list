package com.simongo.todolist.hibernate;

import com.simongo.todolist.model.Building;
import com.simongo.todolist.model.Task;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {

		if (sessionFactory == null){
			try {
				sessionFactory = new Configuration()
						.configure("hibernate.cfg.xml")
						.addAnnotatedClass(Building.class)
						.addAnnotatedClass(Task.class)
						.buildSessionFactory();
			} catch (Throwable ex) {
				throw new ExceptionInInitializerError(ex);
			}
		}

		return sessionFactory;
	}


}
