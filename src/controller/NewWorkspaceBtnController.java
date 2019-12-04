package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainView;
import view.NewWorkspaceView;

public class NewWorkspaceBtnController implements EventHandler<ActionEvent> {

	private MainView view;

	public NewWorkspaceBtnController(MainView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent event) {
		NewWorkspaceView.getInstance();
		view.close();
	}

}
