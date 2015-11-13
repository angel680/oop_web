package controler;

import model.Student;
import dbaccess.DataAccess;

public class StudentManager {
	private String dbpath;
	public StudentManager(String dbpath) {
		this.dbpath = dbpath;
	}
	
	public boolean checkID(String userID){
		DataAccess da = new DataAccess(dbpath);
		Student find =  da.findStudent(userID);
		
		if (find == null) {
			return false;
		}else {
			return true;
		}
	}
	
	public boolean cheakEmail(String userEmail){
		DataAccess da = new DataAccess(dbpath);
		Student find = da.findStudentbyEmail(userEmail);
		
		if(find == null){
			return false;
		}else{
			return true;
		}
	}
	
	public boolean studentRegister(Student student){
		
		DataAccess da = new DataAccess(dbpath);
		return da.addStudent(student);
	}
	
	
}
