package com.simongo.todolist.pages;

import com.simongo.todolist.Panel.NavbarPanel;
import com.simongo.todolist.hibernate.HibernateUtil;
import com.simongo.todolist.model.Building;
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
 * Created by simon on 04/10/16.
 */
public class ListBuildingsPage extends WebPage {

	private ModalWindow modalWindow;

	public ListBuildingsPage() {

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
				setResponsePage(new ListBuildingsPage());
			}
		});

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Building> list = session.createQuery("from Building").list();
		session.getTransaction().commit();

		ListView buildingList = new ListView("buildingList", list) {
			protected void populateItem(ListItem item) {

				final Building building = (Building) item.getModel().getObject();

				Link buildingLink = new Link<Void>("buildingLink")
				{
					@Override
					public void onClick()
					{
						setResponsePage(new BuildingPage(building));
					}
				};

				Label name = new Label("name", building.getName());
				Label address = new Label("address", building.getAddress());
				Label description = new Label("description", building.getDescription());
				buildingLink.add(name);
				item.add(address);
				item.add(description);

				item.add(buildingLink);
			}
		};

		buildingList.setMarkupId("list-group");

		add(new NavbarPanel("navbar"));
		add(buildingList);
		add(new AjaxLink<String>("addBuilding"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				modalWindow.show(target);
			}
		});
		add(modalWindow);

	}
}
