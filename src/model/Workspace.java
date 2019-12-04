package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Workspace {

	private String name;
	private int daysConsidered;
	private int daysCounted;
	private ArrayList<Task> tasks;
	private ArrayList<Entry> entries;
	private int goal;

	public Workspace(String name) {
		this.name = name;
		goal = 36000;
		daysConsidered = 50;
		daysCounted = 50;
		tasks = new ArrayList<>();
		entries = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	public void load() {
		readFile(name + ".txt");

	}

	private void readFile(String string) {

		try {
			FileReader fr = new FileReader(string);
			BufferedReader br = new BufferedReader(fr);
			read(br);
		} catch (Exception e) {
			try {
				PrintWriter pw = new PrintWriter(name + ".txt");
				pw.println(daysConsidered);
				pw.println(daysCounted);
				pw.println(goal);
				pw.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private void read(BufferedReader br) throws Exception {

		String input = br.readLine();
		daysConsidered = Integer.parseInt(input);
		input = br.readLine();
		daysCounted = Integer.parseInt(input);
		input = br.readLine();
		goal = Integer.parseInt(input);
		input = br.readLine();

		while (input != null) {

			String[] data = input.split(",");
			if (data[0].equals("D")) {
				String dateString = data[1];
				String timeString = data[2];
				Entry e = new Entry(dateString, timeString);
				entries.add(e);

			} else if (data[0].equals("T")) {
				String name = data[1];
				String priority = data[2];
				String info = data[3];
				Task t = new Task(name, info.replaceAll("command.enter", "\n"), priority);
				tasks.add(t);
			}
			input = br.readLine();
		}

	}

	public int getDaysConsidered() {
		return daysConsidered;
	}

	public int getDaysCounted() {
		return daysCounted;
	}

	public int getGoal() {
		return goal;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public ArrayList<Entry> getEntries() {

		return entries;
	}

	public void save() {
		try {
			PrintWriter pw = new PrintWriter(name + ".txt");
			pw.println(daysConsidered);
			pw.println(daysCounted);
			pw.println(goal);

			for (Entry e : entries) {
				pw.println("D," + e.getDate() + "," + e.getTime());
			}

			for (Task t : tasks) {
				pw.println("T," + t.getName() + "," + t.getPriority() + "," + t.getCustomInfo());
			}

			pw.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

	}

	public void setConfiguration(int daysConsidered2, int daysCounted2, int goal2) {
		this.daysConsidered = daysConsidered2;
		this.daysCounted = daysCounted2;
		this.goal = goal2;

	}

	public boolean timeExists(String date) {
		for (Entry e : entries) {
			if (e.getDate().equals(date)) {
				return true;
			}

		}
		return false;
	}

	public Entry getEntry(String date) {
		for (Entry e : entries) {
			if (e.getDate().equals(date)) {
				return e;
			}

		}
		return null;
	}

	public void sortDates() {
		Collections.sort(entries);

	}

	public void calculateScores() {

		for (int i = entries.size() - 1; i >= 0; i--) {
			int sum = 0;
			for (int j = 0; j < daysConsidered; j++) {
				if (j + i < entries.size()) {
					sum += entries.get(i + j).getTime();
				}
			}

			entries.get(i).setScore(sum / daysConsidered);
		}

	}
}
