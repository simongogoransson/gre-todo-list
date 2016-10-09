package com.simongo.todolist.pages;

import com.simongo.todolist.panel.NavbarPanel;
import org.apache.wicket.markup.html.WebPage;

/**
 * Created by simon on 04/10/16.
 */

public class StartPage extends WebPage {

	public StartPage() {

		add(new NavbarPanel("navbar"));

	}
}
