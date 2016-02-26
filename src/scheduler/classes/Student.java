package scheduler.classes;

import java.time.LocalTime;
//import java.util.Date;

public class Student {
	
//	private Date date;
	private String day;
	private LocalTime time;
	private String name;
	
	public Student() {
		
	}
	
	public Student(String day, LocalTime time, String name) {
		super();
		this.day = day;
		this.time = time;
		this.name = name;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
