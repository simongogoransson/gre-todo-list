package com.simongo.test;

import com.simongo.todolist.model.Building;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by simon on 01/10/16.
 */
public class TestModel {

	private static SessionFactory sessionFactory;

	@BeforeClass
	public static void setUp(){
		// create session factory
		sessionFactory = new Configuration()
				.configure("hibernate-dbh2.cfg.xml")
				.addAnnotatedClass(Building.class)
				.buildSessionFactory();
	}

	@Test
	public void testDropCreateInsertIntoBuildingTable(){
		Session session = sessionFactory.getCurrentSession();
		try {
			Building building = new Building("Building 123", "Lorem Street 12, Dubai", "A tall building",0,0);
			session.beginTransaction();
			session.save(building);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
	}

	@Test
	public void testSelectFromBuildingTable(){
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			Building building = session.get(Building.class, 1);
			Assert.assertTrue(building.getName().equals("Building 123"));
		} finally {
			session.close();
		}
	}

	@AfterClass
	public static void CleanUp(){
		sessionFactory.close();
	}

}


