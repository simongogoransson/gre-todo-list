package com.simongo.todolist.panel;

import com.simongo.todolist.pages.LoginPage;
import com.simongo.todolist.pages.StartPage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

/**
 * Navigation top bar, displayed on all pages.
 * Used to navigate between pages.
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
				setResponsePage(new LoginPage());
			}
		};

		add(linkStart);
		add(logoLink);
		add(linkApp);


	}
}
