package view;

import controller.CancelActionBtnController;
import controller.ConfirmBtnController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Task;
import model.Workspace;

public class MoreDetailsView extends Stage {

	private static MoreDetailsView instance = null;
	private Scene scene;
	private Button cancelActionBtn;
	private Workspace w;
	private Label tasklbl;
	private TextField taskTxtfld;
	private Label prioritylbl;
	private TextField priorityTxtfld;
	private TextArea txtArea;
	private VBox vbox1;
	private VBox vbox2;
	private VBox mainVbox;
	private HBox hbox1;
	private HBox btnHbox;
	private Button confirmBtn;
	private Task t;

	public MoreDetailsView() {
		w = DisplayView.getWorkspace();

		tasklbl = new Label("Task:");
		tasklbl.setTextFill(Color.web("#0076a3"));
		taskTxtfld = new TextField();
		prioritylbl = new Label("Priority:");
		prioritylbl.setTextFill(Color.web("#0076a3"));
		priorityTxtfld = new TextField();
		txtArea = new TextArea();
		txtArea.prefWidth(500);

		confirmBtn = new Button("confirm");
		confirmBtn.setOnAction(new ConfirmBtnController(this, w));

		cancelActionBtn = new Button("cancel");
		cancelActionBtn.setOnAction(new CancelActionBtnController(this, w));

		vbox1 = new VBox();
		vbox1.setSpacing(10);
		vbox1.setPadding(new Insets(10, 0, 0, 0));
		vbox1.setAlignment(Pos.CENTER);
		vbox1.getChildren().addAll(tasklbl, prioritylbl);

		vbox2 = new VBox();
		vbox2.setSpacing(10);
		vbox2.setPadding(new Insets(10, 0, 0, 0));
		vbox2.setAlignment(Pos.CENTER);
		vbox2.getChildren().addAll(taskTxtfld, priorityTxtfld);

		hbox1 = new HBox();
		hbox1.setSpacing(10);
		hbox1.setPadding(new Insets(10, 0, 0, 0));
		hbox1.setAlignment(Pos.CENTER);
		hbox1.getChildren().addAll(vbox1, vbox2);

		btnHbox = new HBox();
		btnHbox.setSpacing(10);
		btnHbox.setPadding(new Insets(10, 0, 0, 0));
		btnHbox.setAlignment(Pos.CENTER);
		btnHbox.getChildren().addAll(confirmBtn, cancelActionBtn);

		mainVbox = new VBox();
		mainVbox.setSpacing(10);
		mainVbox.setPadding(new Insets(10, 0, 0, 0));
		mainVbox.setAlignment(Pos.CENTER);
		mainVbox.getChildren().addAll(hbox1, txtArea, btnHbox);

		Pane root = new Pane();

		StackPane holder = new StackPane();
		Canvas canvas = new Canvas(600, 350);

		holder.getChildren().addAll(canvas, mainVbox);
		holder.setAlignment(Pos.CENTER);

		root.getChildren().add(holder);
		holder.setStyle("-fx-background-color: #0F1516");

		scene = new Scene(root);
		setScene(scene);
		setTitle("Add Task");

	}

	public static MoreDetailsView getInstance() {

		if (instance == null) {
			instance = new MoreDetailsView();
		}
		instance.show();
		return instance;

	}

	public String getTaskName() {

		return taskTxtfld.getText();
	}

	public String getInfo() {

		return txtArea.getText();
	}

	public String getPriority() {

		return priorityTxtfld.getText();
	}

	public void populate(Task t) {
		taskTxtfld.setText(t.getName());
		txtArea.setText(t.getInfo());
		priorityTxtfld.setText(t.getPriority());
		this.t = t;
	}

	public Task getTask() {
		return t;
	}

}