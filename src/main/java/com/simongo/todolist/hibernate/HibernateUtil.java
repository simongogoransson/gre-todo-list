package com.simongo.todolist.hibernate;

import com.simongo.todolist.model.Building;
import com.simongo.todolist.model.Task;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Util class has one method that helps with returning a session factory.
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;

	/**
	 * Uses the hibernate configuration file to create a session factory.
	 * @return  session factory
	 */
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
