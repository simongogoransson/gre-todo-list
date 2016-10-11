package com.simongo.todolist.pages;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;


/**
 * Wicket webpage for confirming that the creation of a task went well.
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
