package com.simongo;

import com.simongo.todolist.model.Building;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by simon on 01/10/16.
 */
public class TestModel {

	private SessionFactory sessionFactory;

	@Before
	public void setUp(){
		// create session factory
		sessionFactory = new Configuration()
				.configure("hibernate-dbh2.cfg.xml")
				.addAnnotatedClass(Building.class)
				.buildSessionFactory();
	}

	@Test
	public void testDropCreateInsertIntoBuildingTable(){

		// create session
		Session session = sessionFactory.getCurrentSession();

		try {

			Building building = new Building(1,"Building 123", "Lorem Street 12, Dubai", "A tall building");

			// new transaction
			session.beginTransaction();

			// save the building
			session.save(building);

			// commit transaction
			session.getTransaction().commit();

		}
		finally {
			sessionFactory.close();
		}
	}
}


