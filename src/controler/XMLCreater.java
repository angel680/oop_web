package controler;

import java.util.ArrayList;

import model.Bulletin;
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
			xmldoc.append("<bulletMsg>" + bullet.getBulletMsg() +"</bulletMsg>");
			xmldoc.append("<bulletTime>" + bullet.getBulletTime() +"</bulletTime>");
			xmldoc.append("<userName>" + username + "</userName>");
			xmldoc.append("<bulletFavors>" + favorNum + "</bulletFavors>");
			xmldoc.append("</bullet>");
		}
		
		xmldoc.append("</AllBulletins>");
		
		String xml = xmldoc.toString();
		
		da.terminate();
		//System.out.println(xml);
		
		return xml;
	}; 
	
	
}
