package com.simongo.todolist.pages;

import com.simongo.todolist.panel.NavbarPanel;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

public class LoginPage extends WebPage {

	public LoginPage() {

		add(new NavbarPanel("navbar"));

		Form<?> form = new Form("form");
		add(form);

		final TextField<String> user = new TextField<String>("user", Model.of(""));
		user.setRequired(true);

		form.add(user);

		Button button = new Button("submit") {
			@Override
			public void onSubmit() {

				final String userStr = user.getModelObject();
				setResponsePage(new ListBuildingsPage(userStr));
			}
		};
		form.add(button);
	}
}
