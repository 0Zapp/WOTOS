package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.DisplayView;

public class RefreshBtnController implements EventHandler<ActionEvent> {

	private DisplayView view;

	public RefreshBtnController(DisplayView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		view.play();
	}

}