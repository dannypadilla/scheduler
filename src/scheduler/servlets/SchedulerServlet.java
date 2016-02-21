package scheduler.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scheduler.classes.Student;

@WebServlet("/Midterm/Scheduler")
public class SchedulerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SchedulerServlet() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = this.getServletContext();
		if (context.getAttribute("studentList") == null) {
			context.setAttribute("studentList", new ArrayList<Student>() );
		}
		
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		ServletContext context = this.getServletContext();
		
		ArrayList<Student> studentList = (ArrayList<Student>) context.getAttribute("studentList");
		
		PrintWriter out = response.getWriter();
		
		// initialize start time of open scheduling
		int startTime = 10;
		// initialize range of hours to take reservations (scheduling)
		int hoursOfOpenSchedule = 7 + 1;
		
		// choose how often to offer time slots for reservations (or duration to allow space/time scheduling)
		boolean everyHour = false; // schedule every hour ? don't think i need this
		boolean everyHalfHour = true; // schedule every 30 minutes
		boolean everyQuarterHour = false; // schedule every 15 minutes
		
		ArrayList<LocalTime> localTime = new ArrayList<LocalTime>();
		for(int i = 0; i < hoursOfOpenSchedule; i++) {
			
			localTime.add(LocalTime.of(startTime, 00) );

			if(everyHalfHour) {
				localTime.add(LocalTime.of(startTime, 30) );
			} else if (everyQuarterHour) {
				localTime.add(LocalTime.of(startTime, 15) );
				localTime.add(LocalTime.of(startTime, 15) );
				localTime.add(LocalTime.of(startTime, 15) );
			}
			
			startTime++;
		}
		
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("	<head>");
		out.println("		<meta charset=\"utf-8\">");
		out.println("		<title>Presentation Scheduler</title>");
		out.println("		<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
		out.println("		<link rel=\"stylesheet\" href=\"http://albertcervantes.com/cs320/cdn/homework2/styles/animate.css\">");
		out.println("		<link rel=\"stylesheet\" href=\"http://albertcervantes.com/cs320/cdn/homework2/styles/main2.css\" />");
		out.println("	</head>");
		out.println("	<body>");
		out.println("		<div class=\"container\">");
		out.println("			<div class=\"row\">");
		out.println("				<div class=\"col-sm-offset-2 col-sm-8 newsletter-form animated fadeInUp\">");
		out.println("					<div class=\"well\">");
		out.println("						<h1>Presentation Scheduler</h1>");
		out.println("						<h3><small>Signup for a time slot</small></h3>");
		out.println("						<hr />");
		out.println("            <form action=\"Signup\" method=\"post\">");
		out.println("						<table class=\"table table-bordered table-striped table-hover\">");
		out.println("							<tr>");
		out.println("								<th>Time</th>");
		out.println("								<th>Monday</th>");
		out.println("								<th>Tuesday</th>");
		out.println("								<th>Wednesday</th>");
		out.println("								<th>Thursday</th>");
		out.println("								<th>Friday</th>");
		out.println("							</tr>");
		
		int countRow = 0;
		String amPM = "AM";
		
		if (everyHalfHour) {
			hoursOfOpenSchedule *= 2;
		} else if (everyQuarterHour) {
			hoursOfOpenSchedule *= 4;
		}
		
		for (int i = 0; i < hoursOfOpenSchedule; i++) {
			out.println("							<tr>");
			
			out.println("								<td>" + localTime.get(i).format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) +
														"<input type = \"hidden\" name=\"time\" value=\"enter\">" +
														"</td>");
			out.println("								<td>" + "<input type=\"submit\" value=\"Search\" />" +
														"<input type = \"hidden\" name=\"day\" value=\"Monday\">" +
														"</td>");
			out.println("								<td>" + "<input type=\"submit\" value=\"Search\" />" +
														"<input type = \"hidden\" name=\"day\" value=\"Tuesday\">" +
														"</td>");
			out.println("								<td>" + "<input type=\"submit\" value=\"Search\" />" +
														"<input type = \"hidden\" name=\"day\" value=\"Wednesday\">" +
														"</td>");
			out.println("								<td>" + "<input type=\"submit\" value=\"Search\" />" +
														"<input type = \"hidden\" name=\"day\" value=\"Thursday\">" +
														"</td>");
			out.println("								<td>" + "<input type=\"submit\" value=\"Search\" />" +
														"<input type = \"hidden\" name=\"day\" value=\"Friday\">" +
														"</td>");
			
			out.println("								<input type = \"hidden\" name=\"row\" value=\" " + countRow + " \">");
			out.println("								<input type = \"hidden\" name=\"hour\" value=\" " + localTime.get(i) + " \">");
			out.println("								<input type = \"hidden\" name=\"amPM\" value=\" " + amPM + " \">");
			countRow++;
			
			out.println("							</tr>");
			
		}
		out.println("						</table>");
		out.println("						</form>");
		out.println("						<p class=\"text-center\">");
		out.println("							<a href=\"NewsletterSignup\">Back to Sign-Up</a>");
		out.println("						</p>");
		out.println("					</div>");
		out.println("				</div>");
		out.println("			</div>");
		out.println("		</div>");
		out.println("	</body>");
		out.println("</html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String time = (String) request.getParameter("time");
//		String date = (String) request.getParameter("day");
//		String countRow = (String) request.getParameter("countRow");
//		String hour = (String) request.getParameter("hour");
//		String minute = (String) request.getParameter("minute");
//		String amPM = (String) request.getParameter("amPM");
		
		String time = (String) request.getParameter("time");
		String date = (String) request.getParameter("day");
		String countRow = (String) request.getParameter("countRow");
		String hour = (String) request.getParameter("hour");
		String minute = (String) request.getParameter("minute");
		String amPM = (String) request.getParameter("amPM");
		
		Student student = new Student();
		
		student.setTime(time);
		
		request.setAttribute("student", student);
		
//		ArrayList<Student> studentList = (ArrayList<Student>) request.getAttribute("studentList");
		
		
		
		
		doGet(request, response);
	}

}
