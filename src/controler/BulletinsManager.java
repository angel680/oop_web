package controler;

import java.text.DateFormat;
import java.util.Locale;

import model.Bulletin;
import model.Comment;
import model.Favor;
import dbaccess.DataAccess;

public class BulletinsManager {
	
	private String dbpath;
	public BulletinsManager(String dbpath) {
		this.dbpath = dbpath;
	}
	
	public boolean deleteBulletin(int bulletID){
		DataAccess da = new DataAccess(dbpath);
		boolean issucceed = da.deleteBulletin(bulletID);
		da.terminate();
		return issucceed;
	}
	
	public Bulletin getBulletin(int bulletID){
		DataAccess da = new DataAccess(dbpath);
		Bulletin bt = da.findBulletin(bulletID);
		da.terminate();
		return bt;
	}
	
	
	public boolean addBulletin(String bulletTitle,  String bulletMsg,String userID){
		DataAccess da = new DataAccess(dbpath);
		String bulletTime = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,
				Locale.CHINESE).format(new java.util.Date());
		
		Bulletin toadd = new Bulletin(0, bulletTitle, bulletMsg, bulletTime, userID);
		boolean succeed =  da.addBulletin(toadd);
		return succeed;
		
	}
	
	public boolean updateBulletin(int bulletID,String bulletTitle, String bulletMsg,String userID){
		
		DataAccess da = new DataAccess(dbpath);
		
		String bulletTime = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,
				Locale.CHINESE).format(new java.util.Date());

		Bulletin toupdate = new Bulletin(bulletID,bulletTitle, bulletMsg, bulletTime, userID);
		boolean succeed = da.updateBulletin(toupdate);
		da.terminate();
		return succeed;
		
	}
	public boolean addcommentToBulletin(String commentMsg, String userID, int bulletID){
		DataAccess da = new DataAccess(dbpath);
		String commentTime = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,
				Locale.CHINESE).format(new java.util.Date());
		
		Comment toadd = new Comment(0, commentMsg, commentTime, userID, bulletID);
		boolean succeed = da.addComment(toadd);
		da.terminate();
		return succeed;
	}
	
	public boolean addFavorToBulletin(String userID, int bulletID){
		DataAccess da = new DataAccess(dbpath);
		String favorTime = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG,
				Locale.CHINESE).format(new java.util.Date());
		Favor toadd = new Favor(0, favorTime, userID, bulletID);
		boolean succeed = da.addFavor(toadd);
		da.terminate();
		return succeed;
	}
	
	public int findFavorByBulletinAndUser(String userID, int bulletID) {
		DataAccess da = new DataAccess(dbpath);
		Favor find =  da.findFavorByUserAndBulletin(userID, bulletID);
		da.terminate();
		if (find == null) {
			return -1;
		}else {
			return find.getFavorID();
		}
		
	}
	
	public boolean deleteFavorByTwoID(String userID, int bulletID) {
		DataAccess da = new DataAccess(dbpath);
		boolean succeed = da.deleteFavor(userID, bulletID);
		da.terminate();
		return succeed;
	}
	
	
}
