package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Workspace;
import view.DisplayView;
import view.MainView;

public class OpenBtnController implements EventHandler<ActionEvent> {

	private MainView view;

	public OpenBtnController(MainView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		Workspace w = view.getSelectedWorkspace();
		if (w != null) {
			view.close();
			DisplayView.getInstance(w);
		}
	}
}