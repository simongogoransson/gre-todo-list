package com.simongo.todolist.pages;

import com.simongo.todolist.component.TaskStatusLink;
import com.simongo.todolist.constants.TaskConstants;
import com.simongo.todolist.hibernate.HibernateUtil;
import com.simongo.todolist.model.Building;
import com.simongo.todolist.model.Task;
import com.simongo.todolist.panel.NavbarPanel;
import java.util.List;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
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
				return new AddTaskPage(newTaskWindow, building);
			}
		});
		newTaskWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			@Override
			public void onClose(AjaxRequestTarget ajaxRequestTarget) {
				setResponsePage(new BuildingPage(building));
			}
		});

		add(newTaskWindow);

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Task> list = session.createQuery("from Task where buildingId=" +building.getId()).list();
		session.getTransaction().commit();

		ListView taskList = new ListView("taskList", list) {
			protected void populateItem(ListItem item) {

				final Task task = (Task) item.getModel().getObject();

			/*	Link buildingLink = new Link<Void>("buildingLink")
				{
					@Override
					public void onClick()
					{
						setResponsePage(new BuildingPage(task));
					}
				};*/

				Label name = new Label("name", task.getName());
				Label description = new Label("description", task.getDescription());
				Label address = new Label("status", task.getStatus());
				item.add(name);
				item.add(address);
				item.add(description);

				item.add( new TaskStatusLink("startedButton", task, building, TaskConstants.STATUS_STARTED));
				item.add( new TaskStatusLink("completedButton", task, building, TaskConstants.STATUS_COMPLETED));
				item.add( new TaskStatusLink("deleteButton", task, building, TaskConstants.STATUS_DELETED));

			}
		};

		add(taskList);

		add(new AjaxLink<String>("addTask") {
			@Override
			public void onClick(AjaxRequestTarget target) {
				newTaskWindow.show(target);
			}
		});


		Link back = new Link<Void>("back")
		{
			@Override
			public void onClick()
			{
				setResponsePage(new ListBuildingsPage());
			}
		};
		add(back);
	}
}
