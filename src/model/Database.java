package model;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Database {

	private static Database instance;
	private ArrayList<Workspace> workspaces;

	public Database() {
		this.workspaces = new ArrayList<>();
	}

	public static Database getInstance() {

		if (instance == null) {
			instance = new Database();
		}
		return instance;

	}

	public ArrayList<Workspace> getWorkspaces() {
		return workspaces;
	}

	public void saveWorkspaces() {
		try {
			PrintWriter pw = new PrintWriter("workspaces.txt");
			for (Workspace w : Database.getInstance().getWorkspaces()) {
				pw.println(w.getName());
			}
			pw.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
