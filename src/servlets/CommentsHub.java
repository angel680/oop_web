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
 * Servlet implementation class CommentsHub
 */
@WebServlet("/CommentsHub")
public class CommentsHub extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public CommentsHub() {
        super();

    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    }

   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
