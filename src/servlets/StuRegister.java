package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.StudentManager;
import model.Student;


/**
 * Servlet implementation class StuRegister
 */
@WebServlet("/StuRegister")
public class StuRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public void init() throws ServletException
	{
		
	}	 
	
	
	
    public StuRegister() {
        super();
    }

    public void destroy() {
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String reqtype = request.getParameter("reqtype");
		String root = this.getServletConfig().getServletContext().getRealPath("/");
		
		if (reqtype.equals("register")) {
			String userID = request.getParameter("userID");
			String userName = request.getParameter("userName");
			String userPasswd = request.getParameter("userPasswd");
			String userEmail = request.getParameter("userEmail");
			Student toadd = new Student(userID, userName, userPasswd, 0, userEmail);
			StudentManager stumgr = new StudentManager(root + "db/datas.db");
			boolean issucceed =  stumgr.studentRegister(toadd);
					
			
			response.setContentType("text/plain");
			PrintWriter out = response.getWriter();
			out.print(String.valueOf(issucceed));
			out.flush();
			
		}else if(reqtype.equals("checks")){
			String userID = request.getParameter("userID");
			String userEmail = request.getParameter("userEmail");
			
			
			if(userID != null){
				StudentManager stumgr = new StudentManager(root + "db/datas.db");
				
				boolean isexsits = stumgr.checkID(userID);
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				out.print(String.valueOf(isexsits));
				out.flush();
			}
			if(userEmail != null){
				StudentManager stumgr = new StudentManager(root + "db/datas.db");
				
				boolean isexsits = stumgr.cheakEmail(userEmail);
				response.setContentType("text/plain");
				PrintWriter out = response.getWriter();
				out.print(String.valueOf(isexsits));
				out.flush();
			}
			
		
		}
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
