package com.simongo.todolist.pages;

import com.simongo.todolist.constants.TaskConstants;
import com.simongo.todolist.hibernate.HibernateUtil;
import com.simongo.todolist.model.Building;
import com.simongo.todolist.model.Task;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Wicket webpage for adding a new task to the app, the page contains a form.
 */
public class AddTaskPage extends WebPage {

	public AddTaskPage(final ModalWindow window, final Building building, final String user) {

		final Task task = new Task();

		Form<?> form = new Form("form");

		TextField<String> name = new TextField<String>("name", new PropertyModel<String>(task, "name"));
		TextField<String> description = new TextField<String>("description", new PropertyModel<String>(task, "description"));

		Button button = new Button("submit") {

			@Override
			public void onSubmit() {
				super.onSubmit();

				task.setStatus(TaskConstants.STATUS_PENDING);
				task.setBuildingId(building.getId());
				task.setUser(user);

				int numberOfTasks = building.getNumberOfTasks();

				building.setNumberOfTasks(numberOfTasks + 1);

				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

				Session session = sessionFactory.getCurrentSession();

				session.beginTransaction();
				session.save(task);
				session.update(building);
				session.getTransaction().commit();

				setResponsePage(new TaskCreated(window));

			}
		};

		add(form);
		form.add(name);
		form.add(description);
		form.add(button);
	}
}



