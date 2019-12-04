package controller;

import java.util.Date;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import model.Entry;
import model.Task;
import model.Workspace;
import view.AddTaskView;
import view.AddTimeView;
import view.DisplayView;
import view.MoreDetailsView;
import view.SettingsView;

public class ConfirmBtnController implements EventHandler<ActionEvent> {

	private Stage view;
	private Workspace w;

	public ConfirmBtnController(Stage view, Workspace w) {
		this.w = w;
		this.view = view;
	}

	@Override
	public void handle(ActionEvent arg0) {

		if (view instanceof AddTimeView) {
			AddTimeView v = (AddTimeView) view;
			String date = v.getDate();
			int Time = v.getTime();

			if (w.timeExists(date)) {
				Entry entry = w.getEntry(date);
				entry.setDate(date);
				entry.setTime(Time);
			} else {

				Entry entry = new Entry(date, Time);
				w.getEntries().add(entry);

			}
		} else if (view instanceof SettingsView) {
			SettingsView v = (SettingsView) view;
			int daysConsidered = v.getDaysConsidered();
			int daysCounted = v.getDaysCounted();
			int goal = v.getGoalHours();
			w.setConfiguration(daysConsidered, daysCounted, goal);
		} else if (view instanceof AddTaskView) {
			AddTaskView v = (AddTaskView) view;
			String name = v.getTaskName();
			String info = v.getInfo();
			String priority = v.getPriority();
			Task t = new Task(name, info, priority);
			w.getTasks().add(t);

		} else if (view instanceof MoreDetailsView) {
			MoreDetailsView v = (MoreDetailsView) view;
			String name = v.getTaskName();
			String info = v.getInfo();
			String priority = v.getPriority();
			v.getTask().setInfo(info);
			v.getTask().setName(name);
			v.getTask().setPriority(priority);

		}

		DisplayView.getInstance(w).play();
		view.close();
	}
}