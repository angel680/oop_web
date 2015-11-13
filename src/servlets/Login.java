package servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		 String  userID = request.getParameter("userID");
		 String  userPasswd = request.getParameter("userPasswd");
	      
	     System.out.println(userID + userPasswd);		
	      //System.out.println(DataAccess.findStudent("U201417138").getUserEmail());
	      
	      
	      String text = "some text";

	     response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
	     response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
	     response.getWriter().write(text);       // Write response body.
	     response.getWriter().flush();
	 }
	 
	  public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException
	  {
		    
		  
		  
	  }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 System.out.println(this.getServletConfig().getServletContext().getRealPath("/"));
		System.out.println("dopost");
		System.out.println(this.getServletConfig().getServletContext().getRealPath("/"));
	}

}
