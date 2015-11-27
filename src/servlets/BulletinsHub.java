package servlets;

import java.io.IOException;
import java.io.PrintWriter;





import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Student;
import controler.BulletinsManager;
import controler.StudentManager;
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
		
		if (reqtype.equals("modify")) {
			int bulletID = Integer.valueOf(request.getParameter("bulletID"));
			String oper = request.getParameter("operbutton");
			oper=new String(oper.getBytes("ISO-8859-1"),"utf-8");
			
			
			
			
			HttpSession session = request.getSession();
			String userID = (String) session.getAttribute(request.getRemoteAddr());
			if (userID == null) {
				response.sendRedirect("login.html");
			}else {
				if (oper.equals("É¾³ý")) {
					StudentManager stumgr = new StudentManager(path);
					Student operator =  stumgr.getStudent(userID);
					if (operator.getUserAuth() == 0) {
						response.sendRedirect("login.html");
					}else if (operator.getUserAuth() == 1) {
						BulletinsManager bm = new BulletinsManager(path);
						if(bm.deleteBulletin(bulletID)){
							response.sendRedirect("adminChange.html");
						}
						
					}
				}else if (oper.equals("ÐÞ¸Ä")) {
					response.sendRedirect("updateBulletin?bulletID="+bulletID);
				}
				
				
				
				
			}
		}
		
		if(reqtype.equals("addbulletin")){
			HttpSession session = request.getSession();
			String userID = (String) session.getAttribute(request.getRemoteAddr());
			if (userID == null) {
				response.sendRedirect("login.html");
			}else {
				StudentManager stumgr = new StudentManager(path);
				Student operator =  stumgr.getStudent(userID);
				if (operator.getUserAuth() == 0) {
					response.sendRedirect("login.html");
				}else if (operator.getUserAuth() == 1) {
					String bulletTitle = request.getParameter("bulletTitle");
					bulletTitle=new String(bulletTitle.getBytes("ISO-8859-1"),"utf-8");//brain fuck ios-5559 to utf-8
					//System.out.println(bulletTitle);
					String bulletMsg = request.getParameter("bulletMsg"); 
					bulletMsg=new String(bulletMsg.getBytes("ISO-8859-1"),"utf-8");//brain fuck ios-5559 to utf-8
					BulletinsManager bm = new BulletinsManager(path);
					if(bm.addBulletin(bulletTitle, bulletMsg, userID)){
						response.sendRedirect("adminMain.html");
					}
					
				}
			}
		}
		
		//update bulletin
		if(reqtype.equals("update")){
			
			HttpSession session = request.getSession();
			String userID = (String) session.getAttribute(request.getRemoteAddr());
			if (userID == null) {
				response.sendRedirect("login.html");
			}else {
				StudentManager stumgr = new StudentManager(path);
				Student operator =  stumgr.getStudent(userID);
				if (operator.getUserAuth() == 0) {
					response.sendRedirect("login.html");
				}else if (operator.getUserAuth() == 1) {
					int bulletID = Integer.valueOf(request.getParameter("bulletID"));
					String bulletTitle = request.getParameter("bulletTitle");
					bulletTitle=new String(bulletTitle.getBytes("ISO-8859-1"),"utf-8");//brain fuck ios-5559 to utf-8
					//System.out.println(bulletTitle);
					String bulletMsg = request.getParameter("bulletMsg"); 
					bulletMsg=new String(bulletMsg.getBytes("ISO-8859-1"),"utf-8");//brain fuck ios-5559 to utf-8
					BulletinsManager bm = new BulletinsManager(path);
					if(bm.updateBulletin(bulletID, bulletTitle, bulletMsg, userID)){
						response.sendRedirect("adminMain.html");
					}
					
				}
			}
		}
		//add comment
		if(reqtype.equals("addComment")){
			/**/
			String bulletID =request.getParameter("bulletID");
			String userID = request.getParameter("userID");
			String commentMsg = request.getParameter("commentMsg");
			if(bulletID == null || userID == null || commentMsg == null){
				System.out.println("Parameter not get:");
				System.out.println("\t" + bulletID);
				System.out.println("\t" + userID);
				System.out.println("\t" + commentMsg);
			}else {
				/*add comment*/
				BulletinsManager bm = new BulletinsManager(path);
				if(bm.addcommentToBulletin(commentMsg, userID, Integer.valueOf(bulletID))){
					response.setContentType("text/plain;charset=utf-8");
					PrintWriter out = response.getWriter();
					out.print("added");
			        out.flush();
				}else{
					PrintWriter out = response.getWriter();
					out.print("failed");
			        out.flush();
				}
			}
		}
		//add favor
		if(reqtype.equals("addFavor")){
			String bulletID =request.getParameter("bulletID");
			String userID = request.getParameter("userID");
			if(bulletID == null || userID == null){
				System.out.println("Parameter not get:");
				System.out.println("\t" + bulletID);
				System.out.println("\t" + userID);

			}else {
				/*add favor*/
				BulletinsManager bm = new BulletinsManager(path);
				int existFavorID = bm.findFavorByBulletinAndUser(userID, Integer.valueOf(bulletID));
				
				if (existFavorID == -1) {
					if (bm.addFavorToBulletin(userID, Integer.valueOf(bulletID))) {
						response.setContentType("text/plain;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.print("added");
				        out.flush();	
				        System.out.println("addfavor: succeed");
					}else{
						PrintWriter out = response.getWriter();
						out.print("failed");
				        out.flush();
				        //System.out.println("addfavor: faild");
					}
				}else {
					if (bm.deleteFavorByTwoID(userID, Integer.valueOf(bulletID))) {
						response.setContentType("text/plain;charset=utf-8");
						PrintWriter out = response.getWriter();
						out.print("deleted");
				        out.flush();	
				        //System.out.println("deletefavor: succeed");
					}else{
						PrintWriter out = response.getWriter();
						out.print("failed");
				        out.flush();
				        //System.out.println("addfavor: faild");
					}
				}
				
				
				
			}
		}
		
	}

}
