package model;

public class Task {

	private String name;
	private String info;
	private String priority;

	public Task(String name, String info, String priority) {

		this.name = name;
		this.info = info;
		this.priority = priority;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public String getInfo() {
		return info;
	}

	public String getPriority() {
		return priority;
	}

	public String getCustomInfo() {
		
		return info.replaceAll("\n", "command.enter");
	}

}
