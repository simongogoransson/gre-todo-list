package com.simongo.todolist.Panel;

import com.simongo.todolist.pages.ListBuildingsPage;
import com.simongo.todolist.pages.StartPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Created by simon on 04/10/16.
 */
public class NavbarPanel extends Panel{


	public NavbarPanel(String id) {
		super(id);

		Link linkStart = new Link("linkStart") {
			public void onClick() {
				setResponsePage(new StartPage());
			}
		};
		Link logoLink = new Link("logoLink") {
			public void onClick() {
				setResponsePage(new StartPage());
			}
		};
			Link linkApp = new Link("linkApp") {
			public void onClick() {
				setResponsePage(new ListBuildingsPage());
			}
		};
			Link linkAbout = new Link("linkAbout") {
			public void onClick() {
				setResponsePage(new StartPage());
			}
		};

		add(linkStart);
		add(logoLink);
		add(linkApp);
		add(linkAbout);

	}
}
