package controler;

import model.Student;
import dbaccess.DataAccess;

public class LoginChecker {
	public static boolean checkUser(String userID, String userPasswd){
			
		DataAccess da = new DataAccess();
		
		Student find = da.findStudent(userID);
		
		if (find != null) {
			return find.getUserPasswd().equals(userPasswd);
		}else {
			return false;
		}		
		
	}
}
