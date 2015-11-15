package controler;

import java.text.DateFormat;
import java.util.Locale;

import model.Bulletin;
import dbaccess.DataAccess;

public class BulletinsManager {
	
	private String dbpath;
	public BulletinsManager(String dbpath) {
		this.dbpath = dbpath;
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
}
