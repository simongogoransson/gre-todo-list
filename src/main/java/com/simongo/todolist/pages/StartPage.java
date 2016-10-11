package com.simongo.todolist.pages;

import com.simongo.todolist.panel.NavbarPanel;
import org.apache.wicket.markup.html.WebPage;

/**
 * The start page of the application. Displaces the navbar and a information text about the app.
 */

public class StartPage extends WebPage {

	public StartPage() {

		add(new NavbarPanel("navbar"));

	}
}
