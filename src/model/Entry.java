package model;

public class Entry implements Comparable<Entry> {
	private String date;
	private int time;
	private int score;
	private String timeStr;

	public Entry(String date, int time) {
		score = 0;
		this.date = date;
		this.time = time;
		int hrs = time / 3600;
		int mins = time / 60;
		mins = mins % 60;
		this.timeStr = "";
		if (hrs < 10) {
			this.timeStr += "0";
		}
		this.timeStr += hrs + ":";
		if (mins < 10) {
			this.timeStr += "0";
		}
		this.timeStr += mins;
	}

	public Entry(String date) {
		score = 0;
		this.date = date;
		this.time = 0;
		int hrs = time / 3600;
		int mins = time / 60;
		mins = mins % 60;
		this.timeStr = "";
		if (hrs < 10) {
			this.timeStr += "0";
		}
		this.timeStr += hrs + ":";
		if (mins < 10) {
			this.timeStr += "0";
		}
		this.timeStr += mins;
	}

	public String getTimeStr() {
		return timeStr;
	}

	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setTime(int time) {
		this.time = time;
		int hrs = time / 3600;
		int mins = time / 60;
		mins = mins % 60;
		this.timeStr = "";
		if (hrs < 10) {
			this.timeStr += "0";
		}
		this.timeStr += hrs + ":";
		if (mins < 10) {
			this.timeStr += "0";
		}
		this.timeStr += mins;

	}

	public void setScore(int score) {
		this.score = score;
	}

	public Entry(String dateString, String timeString) {
		this.time = Integer.parseInt(timeString);
		this.date = dateString;
		int hrs = time / 3600;
		int mins = time / 60;
		mins = mins % 60;
		this.timeStr = "";
		if (hrs < 10) {
			this.timeStr += "0";
		}
		this.timeStr += hrs + ":";
		if (mins < 10) {
			this.timeStr += "0";
		}
		this.timeStr += mins;
	}

	public String getDate() {
		return date;
	}

	public int getTime() {
		return time;
	}

	public int getScore() {
		return score;
	}

	@Override
	public String toString() {
		return date.toString() + "-" + time;
	}

	@Override
	public int compareTo(Entry e) {
		return e.getDate().compareTo(this.getDate());
	}

}
