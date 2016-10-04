package com.simongo.todolist.pages;

import com.simongo.todolist.hibernate.HibernateUtil;
import com.simongo.todolist.model.Building;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by simon on 02/10/16.
 */
public class AddBuildingPage extends WebPage {

	private static final long serialVersionUID = 2L;

	public AddBuildingPage()
	{

		final Building building = new Building();

		Form<?> form = new Form("form");

		TextField<String> name = new TextField<String>("name", new PropertyModel<String>(building, "name"));
		TextField<String> address = new TextField<String>("address", new PropertyModel<String>(building, "address"));
		TextField<String> description = new TextField<String>("description", new PropertyModel<String>(building, "description"));

		Button button = new Button("submit") {

			@Override
			public void onSubmit() {
				super.onSubmit();

				System.out.println("Name" + building.getName() );

				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

				Session session = sessionFactory.getCurrentSession();

				session.beginTransaction();
				session.save(building);
				session.getTransaction().commit();

			}
		};

		add(form);
		form.add(name);
		form.add(address);
		form.add(description);
		form.add(button);

	}
}
