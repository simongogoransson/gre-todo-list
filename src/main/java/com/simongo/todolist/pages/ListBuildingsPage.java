package com.simongo.todolist.pages;

import com.simongo.todolist.hibernate.HibernateUtil;
import com.simongo.todolist.model.Building;
import com.simongo.todolist.panel.NavbarPanel;
import java.util.List;
import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.hibernate.Session;


/**
 * This page displays a list of building, in the list you can see the name and description of the building
 * and a progressbar on how many of the tasks on the building that is completed.
 */
public class ListBuildingsPage extends WebPage {

	private ModalWindow modalWindow;

	public ListBuildingsPage(final String user) {

		add(new NavbarPanel("navbar"));
		final WebPage listBuildingsPage = this;

		modalWindow = new ModalWindow("modalWindow");
		modalWindow.setPageCreator(new ModalWindow.PageCreator() {
			@Override
			public Page createPage() {
				return new AddBuildingPage(modalWindow, listBuildingsPage);
			}
		});
		modalWindow.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
			@Override
			public void onClose(AjaxRequestTarget ajaxRequestTarget) {
				setResponsePage(new ListBuildingsPage(user));
			}
		});
		add(modalWindow);

		add(new Label("user", user));

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Building> list = session.createQuery("from Building").list();
		session.getTransaction().commit();

		ListView buildingList = new ListView("buildingList", list) {
			// Setting all the files and data on the list item.
			protected void populateItem(ListItem item) {

				final Building building = (Building) item.getModel().getObject();

				Link buildingLink = new Link<Void>("buildingLink")
				{
					@Override
					public void onClick()
					{
						setResponsePage(new BuildingPage(building, user));
					}
				};

				Label name = new Label("name", building.getName());
				buildingLink.add(name);
				item.add(buildingLink);

				Label address = new Label("address", building.getAddress());
				Label description = new Label("description", building.getDescription());
				item.add(address);
				item.add(description);

				WebMarkupContainer progressBar = new WebMarkupContainer("progressBar") {

					@Override
					protected void onComponentTag(ComponentTag tag) {
						tag.put("aria-valuenow", building.getNumberOfCompleted());
						tag.put("aria-valuemin", 0);
						tag.put("aria-valuemax", building.getNumberOfTasks());


						if (building.getNumberOfTasks() != 0) {
							int percentage = (building.getNumberOfCompleted() * 100 / building.getNumberOfTasks());
							tag.put("style","width:"+percentage+"%");
						} else {
							tag.put("hidden", "true");
						}

						super.onComponentTag(tag);
					}
				};

				Label nrTasks = new Label("nrTasks", building.getNumberOfTasks());
				Label completed = new Label("completed", building.getNumberOfCompleted());
				progressBar.add(nrTasks);
				progressBar.add(completed);

				item.add(progressBar);


			}
		};

		add(buildingList);
		add(new AjaxLink<String>("addBuilding"){
			@Override
			public void onClick(AjaxRequestTarget target) {
				modalWindow.show(target);
			}
		});
		add(new Link("myTasks") {
			@Override
			public void onClick() {
				setResponsePage(new MyTasks(user));
			}
		});
	}
}
