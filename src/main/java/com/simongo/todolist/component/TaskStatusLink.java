package com.simongo.todolist.component;

import com.simongo.todolist.constants.TaskConstants;
import com.simongo.todolist.hibernate.HibernateUtil;
import com.simongo.todolist.model.Building;
import com.simongo.todolist.model.Task;
import org.apache.wicket.markup.html.link.Link;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by simon on 08/10/16.
 */
public class TaskStatusLink extends Link {

	private Task task;
	private Building building;
	private int status;

	public TaskStatusLink(String id) {
		super(id);
	}

	public TaskStatusLink(String id, Task task, Building building, int status) {
		super(id);
		this.task = task;
		this.building = building;
		this.status = status;
	}

	@Override
	public void onClick() {
		task.setStatus(status);

		if (status == TaskConstants.STATUS_COMPLETED){
			int numberOfCompleted = building.getNumberOfCompleted();
			building.setNumberOfCompleted(numberOfCompleted + 1);
		}

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		session.beginTransaction();
		session.update(building);
		session.update(task);
		session.getTransaction().commit();
	}

}
