package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileWorker {

	private static FileWorker instance;

	private Database database;

	private FileWorker() {

		database = Database.getInstance();
		readFromFile("workspaces.txt");

	}

	private void readFromFile(String string) {

		try {

			FileReader fr = new FileReader(string);
			BufferedReader br = new BufferedReader(fr);
			readAllFromFile(br);

		} catch (Exception e) {
			try {
				PrintWriter pw = new PrintWriter("workspaces.txt");
				pw.close();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private void readAllFromFile(BufferedReader br) throws IOException {
		String input = br.readLine();
		while (input != null) {
			Database.getInstance().getWorkspaces().add(new Workspace(input));
			input = br.readLine();
		}

	}

	public static FileWorker getInstance() {

		if (instance == null) {
			instance = new FileWorker();
		}
		return instance;

	}

}
