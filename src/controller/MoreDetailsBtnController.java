package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Task;
import view.DisplayView;
import view.MoreDetailsView;

public class MoreDetailsBtnController implements EventHandler<ActionEvent> {

	private DisplayView view;

	public MoreDetailsBtnController(DisplayView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		Task t = view.getTask();
		if (t != null) {
			MoreDetailsView v = MoreDetailsView.getInstance();
			v.populate(t);
			view.close();
		}
	}

}