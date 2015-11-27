package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bulletin;
import controler.BulletinsManager;


@WebServlet("/updateBulletin")
public class updateBulletin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public updateBulletin() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String path = this.getServletConfig().getServletContext().getRealPath("/")+"db/datas.db";
		int bulletID = Integer.valueOf(request.getParameter("bulletID"));
		Bulletin toUpdate =  new BulletinsManager(path).getBulletin(bulletID);
		String content = toUpdate.getBulletMsg();
		String title = toUpdate.getBulletTitle();
		System.out.println(content);
		System.out.println(title);
		
		response.setContentType("text/html;charset = utf-8"); 
		response.setCharacterEncoding("UTF-8");
		PrintWriter output = response.getWriter();
		
		String htmlfile = 
		"<!DOCTYPE html>"+
		"<html>"+
		"<head>"+
		"<meta charset='UTF-8'>"+
		"<title></title>"+
		"<link rel='stylesheet' type='text/css' href='css/adminMain.css'>"+
		"<link rel='stylesheet' type='text/css' href='css/adminAnnounce.css'>"+
		"<script type='text/javascript' src='js/jquery.js'></script>"+
		"</head>"+

		"<body>"+
		"<div id='leftbar'>"+
        	"<img id='logo' src='img/logo.png'>"+
        	"<a class='lefts' id='pic1' href='adminMain.html'></a>"+
        	"<a class='lefts' id='pic2' href='adminAnnounce.html'></a>"+
        	"<a class='lefts' id='pic3' href='adminChange.html'></a>"+
        	"<img id='logo1' src='img/logo1.png'>"+
        "</div>"+
        "<div id='banner'>"+
        	"<img id='logo2' src='img/logo2.png'>"+
        "</div>"+
        "<div id='announceText'>"+
        	"<form method = 'post' action='BulletinsHub?reqtype=update&bulletID="+ toUpdate.getBulletID() +"'>"+
            	"<img id='picTitle' src='img/title.png'>"+
            	"<input id='announceTitle' name='bulletTitle' type='text' value='"+ title +"'>"+
            	"<textarea id='announceContent' name='bulletMsg' >"+ content + "</textarea>"+
            	"<input id='btnAnnounceText' type='submit' value=''>"+
            
        	"</form>"+
        "</div>"+
        "<script type='text/javascript' src='js/adminChanged.js'></script>"+
        "<script type='text/javascript' src='js/adminAnnounce.js'></script>"+
        "</body>"+

		"</html>";
		output.print(htmlfile);
		
	}

}
