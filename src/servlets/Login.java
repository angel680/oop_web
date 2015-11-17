package servlets;

import java.io.IOException;





import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controler.StudentManager;
import controler.XMLCreater;

/**
 * Servlet implementation class Login
 */
@WebServlet(description = "deal with user login", urlPatterns = { "/Login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        //System.out.println("new: ");
       
    }
	 public void init() throws ServletException
	  {
		// String root = this.getServletConfig().getServletContext().getRealPath("/");
		 //DataAccess.init(root + "db/datas.db");
		 
	      // Do required initialization
	     // message = "Hello World";
	  }//

	 
	 public void destory(){
		 //DataAccess.terminate();
	 }
	 public void service(HttpServletRequest request, 
			 HttpServletResponse response) 
            		 throws ServletException, IOException{
		 
		 String path = this.getServletConfig().getServletContext().getRealPath("/")+"db/datas.db";
		 
		 String reqtype = request.getParameter("reqtype");
		 if (reqtype != null) {
			 if (reqtype.equals("getUserInfoByID")) {
				 String  userID = request.getParameter("userID");
				 
				 String xml = XMLCreater.getStudentInfo(userID, path);;
				 response.setCharacterEncoding("utf-8");
			     response.setContentType("text/xml;charset=utf-8");
			     response.setHeader("Cache-control", "no-cache");
			     PrintWriter out = response.getWriter();
			     out.println(xml);
			     out.flush();
			}else if(reqtype.equals("getUserInfoBySession")){
				HttpSession session = request.getSession();
				String userID = (String) session.getAttribute(request.getRemoteAddr());
				String xml = XMLCreater.getStudentInfo(userID, path);;
				response.setCharacterEncoding("utf-8");
			    response.setContentType("text/xml;charset=utf-8");
			    response.setHeader("Cache-control", "no-cache");
			    PrintWriter out = response.getWriter();
			    out.println(xml);
			    out.flush();
			}
			
		 }else {
			 String  userID = request.getParameter("userID");
			 String  userPasswd = request.getParameter("userPasswd");
		     System.out.println("login: " + userID + userPasswd);
			 
			 StudentManager stmgr = new StudentManager(path);
			 
			 if(stmgr.checkLogin(userID, userPasswd)){
			     HttpSession session = request.getSession();
			     session.setAttribute(request.getRemoteAddr(), userID);
			     response.sendRedirect("./userMain.html");
			 }else {
				 response.setContentType("text/plain");
				 PrintWriter out = response.getWriter();
				 out.print("false");
				 out.flush();
			 }
		 }	 
		 
		 
		 
	 }
	 
	  public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	  {
		    
		  
		  
	  }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
