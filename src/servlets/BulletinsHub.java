package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String root = this.getServletConfig().getServletContext().getRealPath("/");
		String xml = XMLCreater.getAllBulletins(root + "db/datas.db");
		response.setCharacterEncoding("utf-8");
        response.setContentType("text/xml;charset=utf-8");
        response.setHeader("Cache-control", "no-cache");
        PrintWriter out = response.getWriter();
        out.println(xml);
        out.flush();
	}

}
