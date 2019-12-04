package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.Database;
import model.Workspace;
import view.MainView;
import view.NewWorkspaceView;

public class CreateBtnController implements EventHandler<ActionEvent> {

	private NewWorkspaceView view;

	public CreateBtnController(NewWorkspaceView view) {
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {
		String name = view.getWorkspaceName();
		boolean flag = true;
		if (!view.getWorkspaceName().isEmpty() && !hasSpaces(name)) {
			for (Workspace w : Database.getInstance().getWorkspaces()) {
				if (w.getName().equals(name)) {
					flag = false;
				}
			}
			if (flag) {
				Workspace workspace = new Workspace(name);
				Database.getInstance().getWorkspaces().add(workspace);
			}
			MainView.getInstance().populateworkspacesCmbx();
			view.close();
		}
	}

	private boolean hasSpaces(String name) {
		for (char c : name.toCharArray()) {
			if (c == ' ') {
				return true;
			}
		}
		return false;
	}

}