package scheduler.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scheduler.classes.Student;

@WebServlet("/Midterm/Signup")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignupServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");		
		ServletContext context = this.getServletContext();
		
		String day = (String) context.getAttribute("day");
		LocalTime time = (LocalTime) context.getAttribute("time");
		
		PrintWriter out = response.getWriter();
		
		out.println("<!doctype html>");
		out.println("<html>");
		out.println("	<head>");
		out.println("		<meta charset=\"utf-8\">");
		out.println("		<title>Presentation Scheduler</title>");
		out.println("		<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css\" integrity=\"sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7\" crossorigin=\"anonymous\">");
		out.println("    <link rel=\"stylesheet\" href=\"http://albertcervantes.com/cs320/cdn/homework2/styles/animate.css\">");
		out.println("		<link rel=\"stylesheet\" href=\"http://albertcervantes.com/cs320/cdn/homework2/styles/main.css\" />");
		out.println("	</head>");
		out.println("");
		out.println("	<body>");
		out.println("		<div class=\"container\">");
		out.println("			<div class=\"row\">");
		out.println("				<div class=\"col-sm-offset-3 col-sm-6 newsletter-form animated bounceInDown\">");
		out.println("");
		out.println("          <div class=\"well text-center\">");
		out.println("						<h1>Presentation Scheduler</h1>");
		out.println("						<hr />");
		
		out.println("");
		out.println("            <form action=\"Signup\" method=\"post\">");
		out.println("");

		out.println("              <div class=\"form-group\">");

		out.println("								<div class=\"input-group\">");
		out.println("									<div class=\"input-group-addon\">");
		out.println("										<p>Request Date & Time:</p>");
		out.println("									</div>");
		out.println("									<div class=\"input-group-addon\" id=\"basic-addon2\">");
		out.println("										<span>" + day + " at " + time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)) + "</span>");
		out.println("									</div>");
		out.println("									<input class=\"form-control\" type=\"hidden\" name=\"dateTime\" />");
		out.println("								</div>");

		out.println("			   </div>");
		out.println("");
		
		if (request.getAttribute("nameError") != null) {
			out.println("              <div class=\"form-group has-error\">");
		} else {
			out.println("              <div class=\"form-group\">");
		}
		
		out.println("								<div class=\"input-group\">");
		out.println("									<div class=\"input-group-addon\">");
		out.println("										<span>Students Name</span>");
		out.println("									</div>");
		out.println("									<input class=\"form-control\" type=\"text\" name=\"name\" placeholder=\"Enter your full name\" />");
		out.println("								</div>");
		out.println("							</div>");
		out.println("");
		out.println("							<input type=\"submit\" class=\"btn btn-info btn-block btn-lg\" value=\"Sign-Up!\" />");
		out.println("						</form>");
		out.println("					</div>");
		out.println("				</div>");
		out.println("			</div>"); 
		out.println("");
		out.println("		</div>");
		out.println("	</body>");
		out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext context = this.getServletContext();
		
		String day = (String) context.getAttribute("day");
		LocalTime time = (LocalTime) context.getAttribute("time") ;
		
		String name = (String) request.getParameter("name");
		
		boolean hasError = false;
		
		if (name == null || name.trim().equals("") ) {
			hasError = true;
		}
		
		if (hasError) {
			request.setAttribute("hasError", "Has Errors");
			doGet(request, response);
			
		} else { 
			
			Cookie cookie = new Cookie("name", name);
			response.addCookie(cookie);
			
			ArrayList<Student> studentList = (ArrayList<Student>) context.getAttribute("studentList");
			studentList.add(new Student(day, time, name) );
			response.sendRedirect("Scheduler");
		}
		
	}

}
