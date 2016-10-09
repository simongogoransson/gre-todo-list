package com.simongo.todolist.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;


/**
 * Created by simon on 07/10/16.
 */
public class TaskCreated extends WebPage {

	public TaskCreated(final ModalWindow window) {

		add(new AjaxLink("close") {
			public void onClick(AjaxRequestTarget target) {
				window.close(target);
			}
		});
	}
}
