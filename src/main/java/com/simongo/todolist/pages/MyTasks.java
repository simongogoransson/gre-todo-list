package com.simongo.todolist.pages;

import com.simongo.todolist.component.TaskStatusLink;
import com.simongo.todolist.constants.TaskConstants;
import com.simongo.todolist.hibernate.HibernateUtil;
import com.simongo.todolist.model.Building;
import com.simongo.todolist.model.Task;
import com.simongo.todolist.panel.NavbarPanel;
import java.util.List;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class MyTasks extends WebPage {

	public MyTasks(final String user) {

		add(new NavbarPanel("navbar"));

		add(new Label("userName", user));

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		String hql = "FROM Task T WHERE T.user = :user_id";
		Query query = session.createQuery(hql);
		query.setParameter("user_id", user);
		List<Task> list = query.list();

		session.getTransaction().commit();

		ListView taskList = new ListView("taskList", list) {
			protected void populateItem(ListItem item) {

				final Task task = (Task) item.getModel().getObject();

				Label name = new Label("name", task.getName());
				Label description = new Label("description", task.getDescription());
				Label user = new Label("user", task.getUser());
				Label status = new Label("status", TaskConstants.getFriendlyStatus(task.getStatus()));

				item.add(name);
				item.add(description);
				item.add(user);

				WebMarkupContainer badge = new WebMarkupContainer("badge") {
					@Override
					protected void onComponentTag(ComponentTag tag) {
						tag.put("class", "label " + TaskConstants.getStatusCssClass(task.getStatus()));
						super.onComponentTag(tag);
					}
				};

				badge.add(status);

				item.add(badge);

				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				session.beginTransaction();
				Building building = session.get(Building.class, task.getBuildingId());
				session.getTransaction().commit();

				WebMarkupContainer dropdownMenu = new WebMarkupContainer("dropdownMenu");

				TaskStatusLink started = new TaskStatusLink("startedButton", task, building, TaskConstants.STATUS_STARTED);
				TaskStatusLink completed = new TaskStatusLink("completedButton", task, building, TaskConstants.STATUS_COMPLETED);
				TaskStatusLink delete = new TaskStatusLink("deleteButton", task, building, TaskConstants.STATUS_DELETED);

				if (task.getStatus() == TaskConstants.STATUS_PENDING ){
					completed.setVisible(false);
				} else if (task.getStatus() == TaskConstants.STATUS_STARTED) {
					started.setVisible(false);
				} else if (task.getStatus() == TaskConstants.STATUS_COMPLETED){
					started.setVisible(false);
					completed.setVisible(false);
					delete.setVisible(false);
					dropdownMenu.setVisible(false);
				}

				dropdownMenu.add(started);
				dropdownMenu.add(completed);
				dropdownMenu.add(delete);

				item.add(dropdownMenu);

			}
		};

		add(taskList);

		Link back = new Link<Void>("back")
		{
			@Override
			public void onClick()
			{
				setResponsePage(new ListBuildingsPage(user));
			}
		};
		add(back);
	}
}
