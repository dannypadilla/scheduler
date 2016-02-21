package scheduler.classes;

import java.util.Date;

public class Student {
	
	private Date date;
	private String time;
	private String name;
	private int row;
	private int column;
	
	public Student() {
		
	}
	
	public Student(Date date, String time, String name) {
		super();
		this.date = date;
		this.time = time;
		this.name = name;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
