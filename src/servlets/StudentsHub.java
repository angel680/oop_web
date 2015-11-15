package servlets;

import java.io.IOException;

import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/StudentsHub")
public class StudentsHub extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public StudentsHub() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String whois = (String) session.getAttribute(request.getRemoteAddr());
		
		System.out.println("check who is:" + whois);
		
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
