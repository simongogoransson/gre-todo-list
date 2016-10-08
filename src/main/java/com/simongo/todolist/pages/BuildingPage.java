package com.simongo.todolist.pages;

import com.simongo.todolist.Panel.NavbarPanel;
import com.simongo.todolist.hibernate.HibernateUtil;
import com.simongo.todolist.model.Building;
import java.util.List;
import org.apache.wicket.Page;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.hibernate.Session;

/**
 * Created by simon on 06/10/16.
 */
public class BuildingPage extends WebPage {

	private ModalWindow newTaskWindow;

	public BuildingPage(final Building building) {

		add(new NavbarPanel("navbar"));

		add(new Label("buildingName", building.getName()));

		newTaskWindow = new ModalWindow("newTaskWindow");
		newTaskWindow.setPageCreator(new ModalWindow.PageCreator() {
			@Override
			public Page createPage() {
				return new AddTaskPage();
			}
		});

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Building> list = session.createQuery("from Building").list();
		session.getTransaction().commit();


	}
}
