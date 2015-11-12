package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class StuRegister
 */
@WebServlet("/StuRegister")
public class StuRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public void init() throws ServletException
	{
		//String root = this.getServletConfig().getServletContext().getRealPath("/");
		//DataAccess.init(root + "db/datas.db");
	}	 
	
	
	
    public StuRegister() {
        super();
    }

    public void destroy() {
		//DataAccess.terminate();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
