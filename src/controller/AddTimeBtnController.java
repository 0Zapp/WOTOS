package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.AddTaskView;
import view.AddTimeView;
import view.DisplayView;

public class AddTimeBtnController implements EventHandler<ActionEvent> {

	private DisplayView view;

	public AddTimeBtnController(DisplayView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		AddTimeView.getInstance();
		view.close();
	}

}