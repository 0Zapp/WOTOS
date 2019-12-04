package view;

import controller.NewWorkspaceBtnController;
import controller.OpenBtnController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Database;
import model.Workspace;

public class MainView extends Stage {

	private static MainView instance = null;
	private HBox hbox;
	private Button openBtn;
	private Button newWorkspaceBtn;
	private ComboBox<Workspace> workspacesCmbx;
	private Scene scene;

	public MainView() {
		Pane root = new Pane();

		workspacesCmbx = new ComboBox<>();
		populateworkspacesCmbx();

		openBtn = new Button("Open");
		openBtn.setOnAction(new OpenBtnController(this));

		newWorkspaceBtn = new Button("New");
		newWorkspaceBtn.setOnAction(new NewWorkspaceBtnController(this));

		hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.getChildren().addAll(workspacesCmbx, openBtn, newWorkspaceBtn);

		StackPane holder = new StackPane();
		Canvas canvas = new Canvas(300, 100);

		holder.getChildren().addAll(canvas, hbox);
		holder.setAlignment(Pos.CENTER);

		root.getChildren().add(holder);
		holder.setStyle("-fx-background-color: #0F1516");

		scene = new Scene(root);
		setScene(scene);
		setTitle("Select Workspace");
	}

	public void populateworkspacesCmbx() {
		workspacesCmbx.getItems().clear();
		workspacesCmbx.getItems().addAll(Database.getInstance().getWorkspaces());
		Database.getInstance().saveWorkspaces();

	}

	public static MainView getInstance() {

		if (instance == null) {
			instance = new MainView();
		}
		instance.show();
		return instance;

	}

	public Workspace getSelectedWorkspace() {
		return workspacesCmbx.getSelectionModel().getSelectedItem();
	}
}
