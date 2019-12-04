package view;

import java.util.Random;
import controller.AddTaskBtnController;
import controller.AddTimeBtnController;
import controller.MoreDetailsBtnController;
import controller.RefreshBtnController;
import controller.SettingsBtnController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Entry;
import model.Task;
import model.Workspace;

public class DisplayView extends Stage {

	private static DisplayView instance = null;
	private Scene scene;
	private VBox mainVbox;
	private HBox mainHbox;
	private HBox hbox1;
	private HBox hbox2;
	private HBox hbox3;

	private VBox vbox1;
	private VBox vbox2;
	private VBox vbox3;
	private TableView<Entry> timesList;
	private TableView<Task> TaskList;
	private Button addTimeBtn;
	private Button settingsBtn;
	private Button addTaskBtn;
	private Button moreDetailsBtn;

	private Label label1;
	private Label scorelbl;

	private Label label2;
	private Label scoreHourslbl;

	private Label label3;
	private Label goalToday;

	private ProgressBar progressBar;

	private Label progresslbl;

	private static Workspace w;
	final NumberAxis xAxis = new NumberAxis();
	final NumberAxis yAxis = new NumberAxis();
	final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);;

	public DisplayView(Workspace w) {
		this.w = w;
		this.w.load();

		populateGraph();

		timesList = new TableView<>();
		TableColumn<Entry, String> date = new TableColumn<>("Date");
		TableColumn<Entry, String> time = new TableColumn<>("Time");

		timesList.getColumns().addAll(date, time);

		date.setCellValueFactory(new PropertyValueFactory<>("date"));
		time.setCellValueFactory(new PropertyValueFactory<>("timeStr"));

		populateTimesList();

		addTimeBtn = new Button("Add Time");
		addTimeBtn.setOnAction(new AddTimeBtnController(this));

		settingsBtn = new Button("Settings");
		settingsBtn.setOnAction(new SettingsBtnController(this));

		vbox1 = new VBox();
		vbox1.setSpacing(10);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setPadding(new Insets(10, 10, 10, 10));
		vbox1.getChildren().addAll(timesList, addTimeBtn, settingsBtn);

		label1 = new Label("Score:");
		label1.setTextFill(Color.web("#0076a3"));
		scorelbl = new Label("no entries");
		scorelbl.setTextFill(Color.web("#0076a3"));

		hbox1 = new HBox();
		hbox1.setSpacing(10);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setPadding(new Insets(10, 10, 10, 10));
		hbox1.getChildren().addAll(label1, scorelbl);

		label2 = new Label("Time average:");
		label2.setTextFill(Color.web("#0076a3"));
		scoreHourslbl = new Label("no entries");
		scoreHourslbl.setTextFill(Color.web("#0076a3"));

		hbox2 = new HBox();
		hbox2.setSpacing(10);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setPadding(new Insets(10, 10, 10, 10));
		hbox2.getChildren().addAll(label2, scoreHourslbl);

		progressBar = new ProgressBar();

		progresslbl = new Label("no entries");
		progresslbl.setTextFill(Color.web("#0076a3"));

		label3 = new Label("Goal for Today:");
		label3.setTextFill(Color.web("#0076a3"));
		goalToday = new Label("no entries");
		goalToday.setTextFill(Color.web("#0076a3"));

		hbox3 = new HBox();
		hbox3.setSpacing(10);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.setPadding(new Insets(10, 10, 10, 10));
		hbox3.getChildren().addAll(label3, goalToday);

		vbox2 = new VBox();
		vbox2.setSpacing(10);
		vbox2.setAlignment(Pos.CENTER);
		vbox2.setPadding(new Insets(10, 10, 10, 10));
		vbox2.getChildren().addAll(hbox1, hbox2, progressBar, progresslbl, hbox3);

		TaskList = new TableView<>();

		TableColumn<Task, String> name = new TableColumn<>("Task");
		TableColumn<Task, String> priority = new TableColumn<>("Priority");

		TaskList.getColumns().addAll(name, priority);

		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		priority.setCellValueFactory(new PropertyValueFactory<>("priority"));

		populateTaskList();

		addTaskBtn = new Button("Add Task");
		addTaskBtn.setOnAction(new AddTaskBtnController(this));

		moreDetailsBtn = new Button("More Details");
		moreDetailsBtn.setOnAction(new MoreDetailsBtnController(this));

		vbox3 = new VBox();
		vbox3.setSpacing(10);
		vbox3.setAlignment(Pos.CENTER);
		vbox3.setPadding(new Insets(10, 10, 10, 10));
		vbox3.getChildren().addAll(TaskList, addTaskBtn, moreDetailsBtn);

		mainHbox = new HBox();
		mainHbox.setSpacing(10);
		mainHbox.setAlignment(Pos.CENTER);
		mainHbox.setPadding(new Insets(10, 10, 10, 10));
		mainHbox.getChildren().addAll(vbox1, vbox2, vbox3);

		mainVbox = new VBox();
		mainVbox.setSpacing(10);
		mainVbox.setAlignment(Pos.CENTER);
		mainVbox.setPadding(new Insets(10, 10, 10, 10));
		mainVbox.getChildren().addAll(lineChart, mainHbox);

		Pane root = new Pane();

		StackPane holder = new StackPane();
		Canvas canvas = new Canvas(1000, 1000);

		holder.getChildren().addAll(canvas, mainVbox);
		holder.setAlignment(Pos.CENTER);

		root.getChildren().add(holder);
		holder.setStyle("-fx-background-color: #0F1516");

		scene = new Scene(root);
		setScene(scene);
		setTitle("Work Assistant");

		scorelbl.setText("no entries");
		play();
	}

	public void populateTaskList() {
		TaskList.getItems().clear();
		TaskList.getItems().addAll(w.getTasks());

	}

	public void populateTimesList() {
		timesList.getItems().clear();
		timesList.getItems().addAll(w.getEntries());

	}

	public static DisplayView getInstance(Workspace w) {

		if (instance == null) {
			instance = new DisplayView(w);
		}
		instance.show();
		return instance;

	}

	public void play() {
		try {
			populateTaskList();
			w.sortDates();
			populateTimesList();
			w.calculateScores();
			scorelbl.setText(w.getEntries().get(0).getScore() + "/" + w.getGoal());
			int scorehrs = (w.getEntries().get(0).getScore() / 3600);
			int scoremins = (w.getEntries().get(0).getScore() / 60);
			scoremins = scoremins % 60;
			int hrsGoal = w.getGoal() / 3600;
			int minsGoal = w.getGoal() / 60;
			minsGoal = minsGoal % 60;
			scoreHourslbl.setText("");
			if (scorehrs < 10) {
				scoreHourslbl.setText(scoreHourslbl.getText() + "0");
			}
			scoreHourslbl.setText(scoreHourslbl.getText() + scorehrs + ":");

			if (scoremins < 10) {
				scoreHourslbl.setText(scoreHourslbl.getText() + "0");
			}
			scoreHourslbl.setText(scoreHourslbl.getText() + scoremins + "/");

			if (hrsGoal < 10) {
				scoreHourslbl.setText(scoreHourslbl.getText() + "0");
			}
			scoreHourslbl.setText(scoreHourslbl.getText() + hrsGoal + ":");

			if (minsGoal < 10) {
				scoreHourslbl.setText(scoreHourslbl.getText() + "0");
			}
			scoreHourslbl.setText(scoreHourslbl.getText() + minsGoal + "");

			progressBar.setProgress((w.getEntries().get(0).getScore() * 1.0) / (w.getGoal() * 1.0));
			progresslbl.setText((round((w.getEntries().get(0).getScore() * 1.0) / (w.getGoal() * 1.0), 4) * 100) + "%");

			int goalScore = w.getGoal();
			int min = 0;

			try {
				min = w.getEntries().get(w.getDaysConsidered() - 1).getTime();
			} catch (Exception e) {

			}
			int calculatedScore = 0;
			if (min < goalScore) {
				calculatedScore = (goalScore + min) / 2 + 300;
			} else {
				calculatedScore = min;
			}
			int goalHrs = calculatedScore / 3600;
			int goalMins = calculatedScore / 60;
			goalMins = goalMins % 60;

			goalToday.setText("");

			if (goalHrs < 10) {
				goalToday.setText(goalToday.getText() + "0");
			}
			goalToday.setText(goalToday.getText() + goalHrs + ":");

			if (goalMins < 10) {
				goalToday.setText(goalToday.getText() + "0");
			}
			goalToday.setText(goalToday.getText() + goalMins);

			populateGraph();

			w.save();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void populateGraph() {
		lineChart.getData().clear();
		// defining a series
		XYChart.Series series = new XYChart.Series();

		series.setName("Score");
		int min = w.getDaysCounted();
		if (w.getEntries().size() < min) {
			min = w.getEntries().size();
		}
		for (int i = 0; i < min; i++) {

			series.getData().add(new XYChart.Data(min - i, w.getEntries().get(i).getScore()));

		}
		Data<Number, Number> horizontalMarker = new Data<>(0, 25);
	
		lineChart.getData().add(series);

	}
	
	  

	public static Workspace getWorkspace() {
		return w;
	}

	public Task getTask() {

		return TaskList.getSelectionModel().getSelectedItem();
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}
}