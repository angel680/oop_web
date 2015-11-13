package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.text.DateFormat;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controler.BulletinsManager;
import controler.XMLCreater;

/**
 * Servlet implementation class BulletinsHub
 */
@WebServlet("/BulletinsHub")
public class BulletinsHub extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BulletinsHub() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
    }


    
    
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reqtype = request.getParameter("reqtype");
		String path = this.getServletConfig().getServletContext().getRealPath("/")+"db/datas.db";
		if (reqtype.equals("getAll")) {
			String xml = XMLCreater.getAllBulletins(path);
			response.setCharacterEncoding("utf-8");
	        response.setContentType("text/xml;charset=utf-8");
	        response.setHeader("Cache-control", "no-cache");
	        PrintWriter out = response.getWriter();
	        out.println(xml);
	        out.flush();
		}
		if(reqtype.equals("update")){
			
			String bulletID =request.getParameter("bulletID");
			String bulletMsg = request.getParameter("bulletMsg");
			String userID = request.getParameter("userID");
			
			if (bulletID == null || bulletMsg == null || userID == null) {
				System.out.println("Parameter not get:");
				System.out.println("\t"+bulletID);
				System.out.println("\t"+bulletMsg);
				System.out.println("\t"+userID);
				return;
			}else {
				BulletinsManager bm = new BulletinsManager(path);
				if(bm.updateBulletin(Integer.valueOf(bulletID), bulletMsg, userID)){
					response.setContentType("text/plain;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.println("updated");
			        out.flush();
				}				
			}
		}
		
	}

}
