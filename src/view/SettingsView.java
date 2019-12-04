package view;

import controller.CancelActionBtnController;
import controller.ConfirmBtnController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Workspace;

public class SettingsView extends Stage {

	private static SettingsView instance = null;
	private Scene scene;
	private Button cancelActionBtn;
	private Button ConfirmBtn;
	private Workspace w;
	private Label goallbl;
	private Label consideredlbl;
	private Label countedlbl;
	private TextField goaltxtfld;
	private TextField consideredtxtfld;
	private TextField countedtxtfld;
	private HBox hbox;
	private HBox btnHbox;
	private VBox vbox1;
	private VBox vbox2;
	private Label empty;

	public SettingsView() {
		w = DisplayView.getWorkspace();

		goallbl = new Label("Set goal seconds:");
		goallbl.setTextFill(Color.web("#0076a3"));
		consideredlbl = new Label("How many days to consider:");
		consideredlbl.setTextFill(Color.web("#0076a3"));
		countedlbl = new Label("How many days to count:");
		countedlbl.setTextFill(Color.web("#0076a3"));
		goaltxtfld = new TextField();
		goaltxtfld.setText(Integer.toString(w.getGoal()));
		consideredtxtfld = new TextField();
		consideredtxtfld.setText(Integer.toString(w.getDaysConsidered()));
		countedtxtfld = new TextField();
		countedtxtfld.setText(Integer.toString(w.getDaysCounted()));
		ConfirmBtn = new Button("Confirm");
		empty = new Label();

		ConfirmBtn.setOnAction(new ConfirmBtnController(this, w));

		cancelActionBtn = new Button("cancel");
		cancelActionBtn.setOnAction(new CancelActionBtnController(this, w));

		btnHbox = new HBox();
		btnHbox.setSpacing(10);
		btnHbox.setPadding(new Insets(10, 0, 0, 0));
		btnHbox.setAlignment(Pos.CENTER);
		btnHbox.getChildren().addAll(ConfirmBtn, cancelActionBtn);

		vbox1 = new VBox();
		vbox1.setSpacing(20);
		vbox1.setPadding(new Insets(10, 0, 0, 0));
		vbox1.setAlignment(Pos.CENTER);
		vbox1.getChildren().addAll(goallbl, consideredlbl, countedlbl, empty);

		vbox2 = new VBox();
		vbox2.setSpacing(10);
		vbox2.setPadding(new Insets(10, 0, 0, 0));
		vbox2.setAlignment(Pos.CENTER);
		vbox2.getChildren().addAll(goaltxtfld, consideredtxtfld, countedtxtfld, btnHbox);

		hbox = new HBox();
		hbox.setSpacing(10);
		hbox.setPadding(new Insets(10, 0, 0, 0));
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(vbox1, vbox2);

		Pane root = new Pane();

		StackPane holder = new StackPane();
		Canvas canvas = new Canvas(400, 200);

		holder.getChildren().addAll(canvas, hbox);
		holder.setAlignment(Pos.CENTER);

		root.getChildren().add(holder);
		holder.setStyle("-fx-background-color: #0F1516");

		scene = new Scene(root);
		setScene(scene);
		setTitle("Settings");

	}

	public static SettingsView getInstance() {

		if (instance == null) {
			instance = new SettingsView();
		}
		instance.show();
		return instance;

	}

	public int getDaysConsidered() {

		return Integer.parseInt(consideredtxtfld.getText());
	}

	public int getDaysCounted() {

		return Integer.parseInt(countedtxtfld.getText());
	}

	public int getGoalHours() {

		return Integer.parseInt(goaltxtfld.getText());
	}

}