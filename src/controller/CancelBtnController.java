package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainView;
import view.NewWorkspaceView;

public class CancelBtnController implements EventHandler<ActionEvent> {

	private NewWorkspaceView view;

	public CancelBtnController(NewWorkspaceView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		MainView.getInstance();
		view.close();
	}

}