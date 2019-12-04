package view;

import java.util.Date;

import controller.CancelActionBtnController;
import controller.ConfirmBtnController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Workspace;

public class AddTimeView extends Stage {

	private static AddTimeView instance = null;
	private Scene scene;
	private Button cancelActionBtn;
	private Workspace w;
	private DatePicker datePicker;
	private Label timelbl;
	private TextField timetxtfld;
	private Button ConfirmBtn;
	private VBox vbox;
	private HBox hbox1;
	private HBox hbox2;

	public AddTimeView() {
		w = DisplayView.getWorkspace();

		datePicker = new DatePicker();
		timelbl = new Label("Time(in seconds):");
		timelbl.setTextFill(Color.web("#0076a3"));
		timetxtfld = new TextField();
		ConfirmBtn = new Button("Confirm");
		ConfirmBtn.setOnAction(new ConfirmBtnController(this, w));

		cancelActionBtn = new Button("Cancel");
		cancelActionBtn.setOnAction(new CancelActionBtnController(this, w));

		hbox1 = new HBox();
		hbox1.setSpacing(10);
		hbox1.setPadding(new Insets(10, 0, 0, 0));
		hbox1.setAlignment(Pos.CENTER);
		hbox1.getChildren().addAll(timelbl, timetxtfld);

		hbox2 = new HBox();
		hbox2.setSpacing(10);
		hbox2.setPadding(new Insets(10, 0, 0, 0));
		hbox2.setAlignment(Pos.CENTER);
		hbox2.getChildren().addAll(ConfirmBtn, cancelActionBtn);

		vbox = new VBox();
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10, 0, 0, 0));
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(datePicker, hbox1, hbox2);

		Pane root = new Pane();

		StackPane holder = new StackPane();
		Canvas canvas = new Canvas(300, 250);

		holder.getChildren().addAll(canvas, vbox);
		holder.setAlignment(Pos.CENTER);

		root.getChildren().add(holder);
		holder.setStyle("-fx-background-color: #0F1516");

		scene = new Scene(root);
		setScene(scene);
		setTitle("Add Time");

	}

	public static AddTimeView getInstance() {

		if (instance == null) {
			instance = new AddTimeView();
		}
		instance.show();
		return instance;

	}

	public String getDate() {

		return datePicker.getValue().toString();
	}

	public int getTime() {

		return Integer.parseInt(timetxtfld.getText());
	}

}