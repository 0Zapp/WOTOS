package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.DisplayView;
import view.SettingsView;

public class SettingsBtnController implements EventHandler<ActionEvent> {

	private DisplayView view;

	public SettingsBtnController(DisplayView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		SettingsView.getInstance();
		view.close();
	}

}