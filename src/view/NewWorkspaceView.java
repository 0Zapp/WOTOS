package view;

import controller.CancelBtnController;
import controller.CreateBtnController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class NewWorkspaceView extends Stage {

	private static NewWorkspaceView instance = null;
	private Scene scene;
	private TextField txtfld;
	private Button createBtn;
	private Button cancelBtn;
	private HBox hbox;

	public NewWorkspaceView() {

		txtfld = new TextField();

		createBtn = new Button("Create");
		createBtn.setOnAction(new CreateBtnController(this));

		cancelBtn = new Button("Cancel");
		cancelBtn.setOnAction(new CancelBtnController(this));

		hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.getChildren().addAll(txtfld, createBtn, cancelBtn);

		Pane root = new Pane();

		StackPane holder = new StackPane();
		Canvas canvas = new Canvas(300, 100);

		holder.getChildren().addAll(canvas, hbox);
		holder.setAlignment(Pos.CENTER);

		root.getChildren().add(holder);
		holder.setStyle("-fx-background-color: #0F1516");

		scene = new Scene(root);
		setScene(scene);
		setTitle("New Workspace");

	}

	public static NewWorkspaceView getInstance() {

		if (instance == null) {
			instance = new NewWorkspaceView();
		}
		instance.show();
		return instance;

	}

	public String getWorkspaceName() {

		return txtfld.getText();
	}

}
