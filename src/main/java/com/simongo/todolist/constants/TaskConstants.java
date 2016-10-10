package com.simongo.todolist.constants;

/**
 * Created by simon on 08/10/16.
 */
public class TaskConstants {

	public static final int STATUS_NOT_CREATED = 0;
	public static final int STATUS_PENDING = 1;
	public static final int STATUS_STARTED = 2;
	public static final int STATUS_COMPLETED = 3;
	public static final int STATUS_DELETED = 10;

	public static String getFriendlyStatus (int status) {
		switch (status) {
			case STATUS_NOT_CREATED:
				return "Not created";
			case STATUS_PENDING:
					return "Pending";
			case STATUS_STARTED:
					return "Started";
			case STATUS_COMPLETED:
					return "Completed";
			case STATUS_DELETED:
					return "Deleted";
		}
		return "";
	}

	public static String getStatusCssClass (int status) {
		switch (status) {
			case STATUS_NOT_CREATED:
				return "label-warning";
			case STATUS_PENDING:
					return "label-info";
			case STATUS_STARTED:
					return "label-primary";
			case STATUS_COMPLETED:
					return "label-success";
			case STATUS_DELETED:
					return "label-danger";
		}
		return "";
	}
}




