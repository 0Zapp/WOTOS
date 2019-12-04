package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Workspace;
import view.DisplayView;

public class CancelActionBtnController implements EventHandler<ActionEvent> {

	private Stage view;
	private Workspace w;

	public CancelActionBtnController(Stage view,Workspace w) {
		this.w=w;
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		DisplayView.getInstance(w);
		view.close();
	}
}