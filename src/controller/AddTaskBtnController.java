package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.AddTaskView;
import view.DisplayView;

public class AddTaskBtnController implements EventHandler<ActionEvent> {

	private DisplayView view;

	public AddTaskBtnController(DisplayView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		AddTaskView.getInstance();
		view.close();
	}

}