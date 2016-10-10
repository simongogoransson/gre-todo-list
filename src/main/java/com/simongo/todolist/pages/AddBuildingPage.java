package com.simongo.todolist.pages;

import com.simongo.todolist.hibernate.HibernateUtil;
import com.simongo.todolist.model.Building;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Wicket webpage for adding a new building to the app, the page contains a form.
 */
public class AddBuildingPage extends WebPage {

	public AddBuildingPage(final ModalWindow window, final WebPage parentPage)
	{

		final Building building = new Building();

		Form<?> form = new Form("form");

		TextField<String> name = new TextField<String>("name", new PropertyModel<String>(building, "name"));
		TextField<String> address = new TextField<String>("address", new PropertyModel<String>(building, "address"));
		TextField<String> description = new TextField<String>("description", new PropertyModel<String>(building, "description"));

		Button button = new Button("submit") {

			/**
			 * Save the building to the database.
			 */
			@Override
			public void onSubmit() {
				super.onSubmit();

				building.setNumberOfCompleted(0);
				building.setNumberOfCompleted(0);
				
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

				Session session = sessionFactory.getCurrentSession();

				session.beginTransaction();
				session.save(building);
				session.getTransaction().commit();

				setResponsePage(new BuildingCreatedPage(window));

			}
		};

		add(form);
		form.add(name);
		form.add(address);
		form.add(description);
		form.add(button);

	}
}
