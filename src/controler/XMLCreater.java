package controler;

import java.util.ArrayList;

import model.Bulletin;
import model.Comment;
import model.Student;
import dbaccess.DataAccess;

public class XMLCreater {
	public static String getAllBulletins(String path){
		DataAccess da = new DataAccess(path);
		StringBuilder xmldoc = new StringBuilder();
		xmldoc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); 
		xmldoc.append("<AllBulletins>");
		ArrayList<Bulletin> bulletArray =  da.getAllBulletins();
		
		for(Bulletin bullet : bulletArray){
			String favorNum =String.valueOf(da.findFavorByBulletin(bullet.getBulletID()).size());
			String username = da.findStudent(bullet.getUserID()).getUserName();
			xmldoc.append("<bullet>");
			
			xmldoc.append("<bulletID>" + String.valueOf(bullet.getBulletID())+"</bulletID>");
			xmldoc.append("<bulletTitle>" + bullet.getBulletTitle() +"</bulletTitle>");
			xmldoc.append("<bulletMsg>" + bullet.getBulletMsg() +"</bulletMsg>");
			xmldoc.append("<bulletTime>" + bullet.getBulletTime() +"</bulletTime>");
			xmldoc.append("<userName>" + username + "</userName>");
			xmldoc.append("<bulletFavors>" + favorNum + "</bulletFavors>");
			xmldoc.append("<allComments>");
			ArrayList<Comment> commentsArray = da.findCommentByBulletin(bullet.getBulletID());
				for(Comment comment : commentsArray){
					String cusername = da.findStudent(comment.getUserID()).getUserName();
					xmldoc.append("<comment>");
					xmldoc.append("<commentID>" + String.valueOf(comment.getCommentID()) + "</commentID>");
					xmldoc.append("<commentMsg>" + comment.getCommentMsg() + "</commentMsg>");
					xmldoc.append("<commentTime>" + comment.getCommentTime()+ "</commentTime>");
					xmldoc.append("<cuserName>" + cusername + "</cuserName>");
					xmldoc.append("</comment>");
				}
			
			xmldoc.append("</allComments>");
			xmldoc.append("</bullet>");
		}
		
		xmldoc.append("</AllBulletins>");
		
		String xml = xmldoc.toString();
		
		da.terminate();
		//System.out.println(xml);
		
		return xml;
	}; 
	
	
	public static String getStudentInfo(String userID, String dbpath){
		DataAccess da = new DataAccess(dbpath);
		StringBuilder xmldoc = new StringBuilder();
		Student stu = da.findStudent(userID);
		da.terminate();
		xmldoc.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xmldoc.append("<info>");
		if (stu != null) {
			xmldoc.append("<Student>");
			xmldoc.append("<userID>" + stu.getUserID() + "</userID>");
			xmldoc.append("<userName>" + stu.getUserName() + "</userName>");
			xmldoc.append("<userPasswd>" + stu.getUserPasswd() + "</userPasswd>");
			xmldoc.append("<userEmail>" + stu.getUserEmail() + "</userEmail>");
			xmldoc.append("<userAuth>" + String.valueOf(stu.getUserAuth()) + "</userAuth>");
			xmldoc.append("</Student>");
		}else {
			xmldoc.append("<Student>");
			xmldoc.append("</Student>");
		}
		xmldoc.append("</info>");
		
		
		return xmldoc.toString();
	}
	
	
}
