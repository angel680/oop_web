package dbaccess;

import java.sql.*;
import java.util.ArrayList;

import model.*;

public class DataAccess {
	
	private Connection m_connet = null;
	private Statement m_statement = null;
	private String url = "jdbc:sqlite:";
	
	public DataAccess() {
		// TODO Auto-generated constructor stub
	}
	
	public DataAccess(String path){
		init(path);
	}
	
	
	public Connection init(String path){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println("LEO LOG: CLASS NOT FOUND");
		}
		
		try {
			m_connet = DriverManager.getConnection(url + path);
		} catch (SQLException e) {
			System.out.println("LEO LOG: CONNECTION NOT GET");
		}
		try {
			m_statement = m_connet.createStatement();
			m_statement.execute("PRAGMA foreign_keys=ON");	
		} catch (SQLException e) {
			System.out.println("LEO LOG: STATEMENT NOT GET");
		
		}
		return m_connet;
	}
	
	public void terminate(){
		try {
			m_statement.close();
			m_connet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public Student findStudent(String userID){
		Student m_student = null;
		
		String sql = "SELECT userName, userPasswd, "
					+ "userAuth, userEmail FROM students "
					+ "WHERE userID = '" + userID +"'";
		try {
			ResultSet result = m_statement.executeQuery(sql);
	
			if (result.next()) {
				String userName = result.getString(1);
				String userPasswd = result.getString(2);
				int userAuth = result.getInt(3);
				String userEmail = result.getString(4);
				m_student = new Student(userID, userName, 
						userPasswd, userAuth, userEmail);
			}else {
				m_student = null;
			}
		} catch (SQLException e) {
			System.out.println("LEO LOG: findStudent SQL EXECUTE FAIL"
					+ "\n\t" + e.getSQLState());
		}		
		return m_student;
	}

	public Bulletin findBulletin(int bulletID){
		Bulletin m_bulletin = null;
		
		String sql = "SELECT bulletMsg, bulletTime, "
					+ "userID FROM bulletins "
					+ "WHERE bulletID = " + String.valueOf(bulletID);
		
		try {
			ResultSet result = m_statement.executeQuery(sql);
			if (result.next()) {
				String bulletMsg = result.getString(1);
				String bulletTime = result.getString(2);
				String userID = result.getString(3);
				m_bulletin = new Bulletin(bulletID, 
						bulletMsg, bulletTime, userID);
			}else {
				m_bulletin = null;
			}
			
		} catch (SQLException e) {
			System.out.println("LEO LOG: findBulletin SQL EXECUTE FAIL"
					+ "\n\t" + e.getSQLState());
			System.out.println(sql);
		}
		
		return m_bulletin;
	}
	
	public Comment findComment(int commentID){
		Comment m_comment = null;
		String sql = "SELECT commentMsg, commentTime, userID, "
					+ "bulletID FROM comments "
					+ "WHERE commentID = " + String.valueOf(commentID);
		try {
			ResultSet result = m_statement.executeQuery(sql);
			if (result.next()) {
				String commentMsg = result.getString(1);
				String commentTime = result.getString(2);
				String userID = result.getString(3);
				int bulletID = result.getInt(4);
				m_comment = new Comment(commentID, commentMsg, commentTime, userID, bulletID);
			
			}else {
				m_comment = null;
			}
			
		} catch (SQLException e) {
			System.out.println("LEO LOG: findComment SQL EXECUTE FAIL"
					+ "\n\t" + e.getSQLState());
		}
		
		return m_comment;
	}
	
	public Favor findFavor(int favorID){
		Favor m_favor = null;
		String sql = "SELECT favorTime, userID, bulletID "
				+ "FROM favors "
				+ "WHERE favorID = " + String.valueOf(favorID);
		
		try {
			ResultSet result = m_statement.executeQuery(sql);
			
			if(result.next()){
				String favorTime = result.getString(1);
				String userID = result.getString(2);
				int bulletID = result.getInt(3);
				
				m_favor = new Favor(favorID, favorTime, userID, bulletID);
			}else {
				m_favor = null;
			}
			
			
		} catch (SQLException e) {
			System.out.println("LEO LOG: findFavor SQL EXECUTE FAIL"
					+ "\n\t" + e.getSQLState());
		}
		
		return m_favor;
	}
	
	public  ArrayList<Comment> findCommentsByUser(String userID){
		ArrayList<Comment> returnArray = new ArrayList<Comment>();
		Comment m_comment;
		
		String sql = "SELECT commentID, commentMsg, "
				+ "commentTime, bulletID FROM comments "
				+ "WHERE userID = '" + userID + "'";
		try {
			ResultSet result = m_statement.executeQuery(sql);
			
			while(result.next()){
				int commentID = result.getInt(1);
				String commentMsg = result.getString(2);
				String commentTime = result.getString(3);
				int bulletID = result.getInt(4);
				m_comment = new Comment(commentID, commentMsg, 
						commentTime, userID, bulletID);
				returnArray.add(m_comment);
			}
			
		} catch (SQLException e) {
			System.out.println("LEO LOG: findCommentsByUser SQL EXECUTE FAIL \n\t+"
					+ e.getSQLState());
		}	
		return returnArray;
	}
	
	public ArrayList<Bulletin> findBulletinsByUser(String userID){
		ArrayList<Bulletin> returnArray = new ArrayList<Bulletin>();
		Bulletin m_Bulletin;
		
		String sql = "SELECT bulletID, bulletMsg, "
				+ "bulletTime FROM bulletins "
				+ "WHERE userID = '" + userID + "'";
		
		try {
			ResultSet result = m_statement.executeQuery(sql);
			while(result.next()){
				int bulletID = result.getInt(1);
				String bulletMsg = result.getString(2);
				String bulletTime = result.getString(3);
				m_Bulletin = new Bulletin(bulletID, bulletMsg,
						bulletTime, userID);
				returnArray.add(m_Bulletin);
			}
			
		} catch (SQLException e) {
			System.out.println("LEO LOG: findBulletinByUser SQL EXECUTE FAIL \n\t" 
								+ e.getSQLState()+ "\n\t" + e.getMessage());
			System.out.println(sql);
		}
		
		return returnArray;
	}

	public ArrayList<Favor> findFavorsByUser(String userID){
		ArrayList<Favor> returnArray = new ArrayList<Favor>();
		Favor m_favor;
		
		String sql = "SELECT favorID, favorTime, "
				+ "bulletID FROM favors "
				+ "WHERE userID = '" + userID + "'";
		
		try {
			ResultSet result = m_statement.executeQuery(sql);
			while(result.next()){
				int favorID = result.getInt(1);
				String favorTime = result.getString(2);
				int bulletID = result.getInt(3);
				m_favor = new Favor(favorID, favorTime, userID, bulletID);
				returnArray.add(m_favor);
			}
		} catch (SQLException e) {
			System.out.println("LEO LOG: findFavorByUser SQL EXECUTE FAIL\n\t" + e.getSQLState());			e.printStackTrace();
		}		
		return returnArray;
	}
	
	

public ArrayList<Comment> findCommentByBulletin(int bulletID){
		ArrayList<Comment> returnArray= new ArrayList<Comment>();
		Comment m_comment;
		
		String sql="SELECT commentID, commentMsg, "
				+"commentTime, userID FROM comments "
				+"WHERE bulletID = " + String.valueOf(bulletID);
		
		try{
			ResultSet result= m_statement.executeQuery(sql);
			while(result.next()){
				int commentID=result.getInt(1);
				String commentMsg=result.getString(2);
				String commentTime=result.getString(3);
				String userID=result.getString(4);
				m_comment=new Comment(commentID, commentMsg, commentTime, userID, bulletID);
				returnArray.add(m_comment);
			}
		}catch(SQLException e){
			System.out.println("LEO LOG: findCommentByBulletin SQL EXECUTE FAIL \n\t" + e.getMessage());
			System.out.println(e.getSQLState());
			System.out.println(sql);
		}
		
		return returnArray;	
	}
	
	public ArrayList<Favor> findFavorByBulletin(int bulletID){
		ArrayList<Favor> returnArray=new ArrayList<Favor>();
		Favor m_favor;
		 
		String sql = "SELECT favorID, favorTime, userID "
				+"FROM favors "
				+"WHERE bulletID = " + String.valueOf(bulletID);
		
		try{
			ResultSet result= m_statement.executeQuery(sql);
			while(result.next()){
				int favorID=result.getInt(1);
				String favorTime=result.getString(2);
				String userID =result.getString(3);
				m_favor=new Favor(favorID, favorTime, userID, bulletID);
				returnArray.add(m_favor);
			}
		}catch(SQLException e){
			System.out.println("LEO LOG: findFavorByBulletin SQL EXECUTE FAIL");
			System.out.println(sql);
		}
		
		return returnArray;
	}

	
	
	
	
	public boolean addStudent(Student student){
		String sql = "INSERT INTO students "
				+ "(userID, userName, userPasswd, "
				+ "userAuth, userEmail) "
				+ "VALUES('" 
					+ student.getUserID()+"', '"
					+ student.getUserName() + "', '"
					+ student.getUserPasswd() + "', "
					+ String.valueOf(student.getUserAuth()) + ", '"
					+ student.getUserEmail() 
				+ "')";
		try {
			m_statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("LEO LOG: addStudent SQL EXECUTE FAIL: \n"
					+ "\t" + e.getMessage());
		}
		return false;
	}
	
	public boolean addBulletin(Bulletin bulletin){
		String sql = "INSERT INTO bulletins "
				+ "(bulletID, bulletMsg, bulletTime, userID) "
				+ "VALUES(null, '"
					+ bulletin.getBulletMsg() + "', '"
					+ bulletin.getBulletTime() + "', '"
					+ bulletin.getUserID() 
				+ "')";
		try {
			m_statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("LEO LOG: addBulletin SQL EXECUTE FAIL: \n"
					+ "\t" + e.getSQLState()
					+ "\n\t" + e.getMessage());
			System.out.println(sql);
		}
		return false;
	}
	
	public boolean addComment(Comment comment){
		String sql = "INSERT INTO comments "
				+ "(commentID, commentMsg, "
				+ "commentTime, userID, bulletID)"
				+ "VALUES( null, '"
					+ comment.getCommentMsg() + "', '"
					+ comment.getCommentTime() + "', '"
					+ comment.getUserID() + "', "
					+ String.valueOf(comment.getBulletID())
				+ ")";
		try {
			m_statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("LEO LOG: addComment SQL EXECUTE FAIL: \n"
					+ "\t" + e.getSQLState());	
			return false;
		}
	}
	
	public boolean addFavor(Favor favor){
		String findSql = "SELECT * FROM favors WHERE userId = '"
				+ favor.getUserID() + "' and bulletID = " + String.valueOf(favor.getBulletID()) ; 
		try {
			ResultSet result = m_statement.executeQuery(findSql);
			if (result.next()) {
				System.out.println("favor alredy exisits");
				return false;
			}
		} catch (SQLException e) {
			System.out.println("LEO LOG: addFavor SQL EXECUTE FAIL: \n"
					+ "\t" + e.getSQLState());			
		}
		
		
		String sql = "INSERT INTO favors"
				+ "(favorID, favorTime, userID, bulletID)"
				+ "VALUES(null, '"
					+ favor.getFavorTime() + "', '"
					+ favor.getUserID() + "', "
					+ String.valueOf(favor.getBulletID())
				+ ")";
		try {
			m_statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("LEO LOG: addFavor SQL EXECUTE FAIL: \n"
					+ "\t" + e.getSQLState());			
		}
		return false;
	}

	public boolean updateStudent(Student student){
		
		String sql = "UPDATE students set "
					+ "userName = '" + student.getUserName() + "', "
					+ "userPasswd = '" + student.getUserPasswd() + "', "
					+ "userAuth = " + String.valueOf(student.getUserAuth()) + ", "
					+ "userEmail = '" + student.getUserEmail()+ "' "
					+ "WHERE userID = '" + student.getUserID() + "'";
		
		try {
			if (m_statement.executeUpdate(sql) == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("LEO LOG: updateStudent SQL EXECUTE FAIL: \n"
					+ "\t" + e.getSQLState());
			return false;
		}
		
		
	}
	
	public boolean updateBulletin(Bulletin bulletin){
		
		String sql = "UPDATE bulletins set "
					+ "bulletMsg = '" + bulletin.getBulletMsg()+ "', "
					+ "bulletTime = '"+ bulletin.getBulletTime() + "', "
					+ "userID = '" + bulletin.getUserID() + "'"
					+ "WHERE bulletID = " 
					+ String.valueOf(bulletin.getBulletID());
		
		try {
			if (m_statement.executeUpdate(sql) == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("LEO LOG: updateBulletin; SQL EXECUTE FAIL: \n"
					+ "\t" + e.getMessage());
			return false;
		}
	}
	
	public boolean updateComments(Comment commet){
		
		String sql = "UPDATE comments set "
					+ "commentMsg = '" + commet.getCommentMsg() + "', "
					+ "commentTime = '" + commet.getCommentTime() + "', "
					+ "userID = '" + String.valueOf(commet.getUserID()) + "', "
					+ "bulletID = " + String.valueOf(commet.getBulletID())
					+ " WHERE commentID = " 
					+ String.valueOf(commet.getCommentID()); 
				
		
		try {
			if (m_statement.executeUpdate(sql) == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("LEO LOG: updateComment; SQL EXECUTE FAIL: \n"
					+ "\t" + e.getMessage());
			return false;
		}
	}
	
	public boolean deleteFavor(Favor favor){
		String sql = "DELETE FROM favors "
					+ "WHERE favorID = "
					+ String.valueOf(favor.getFavorID());
		
		try {
			if (m_statement.executeUpdate(sql) == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("LEO LOG: deleteFavor; SQL EXECUTE FAIL: \n"
					+ "\t" + e.getSQLState());
			return false;
		}
	}
	public boolean deleteFavor(String userID, int bulletID){
		String sql = "DELETE FROM favors "
				+ "WHERE userID = '" + userID + "' "
				+ "and bulletID = " + String.valueOf(bulletID);
	
	try {
		if (m_statement.executeUpdate(sql) == 0) {
			return false;
		}
		return true;
	} catch (SQLException e) {
		System.out.println("LEO LOG: deleteFavor; SQL EXECUTE FAIL: \n"
				+ "\t" + e.getSQLState());
		return false;
	}
	}
	
	
	public boolean deleteComment(int commentID){
		
		String sql = "DELETE FROM comments "
					+ "WHERE commentID = "
					+ String.valueOf(commentID);
		
		try {
			if (m_statement.executeUpdate(sql) == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println("LEO LOG: deleteComment; SQL EXECUTE FAIL: \n"
					+ "\t" + e.getSQLState());
			return false;
		}
	}
	
	
	
	public boolean deleteBulletin(int bulletID){
		
		ArrayList<Comment> commentsToDel = findCommentByBulletin(bulletID);
		ArrayList<Favor> favorToDel = findFavorByBulletin(bulletID);
		
		for(Comment todel : commentsToDel){
			deleteComment(todel.getCommentID());
		}
		
		for(Favor todel : favorToDel){
			deleteFavor(todel.getUserID(), todel.getBulletID());
		}
		
		String sql = "DELETE FROM bulletins "
					+ "WHERE bulletID = " + String.valueOf(bulletID);
		
		try {
			
			if (m_statement.executeUpdate(sql) == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println(sql);
			return false;
		}
				

	}
	public ArrayList<Bulletin> getAllBulletins(){
		ArrayList<Bulletin> returnArray = new ArrayList<Bulletin>();
		Bulletin m_Bulletin;
		
		String sql = "SELECT bulletID, bulletMsg, "
				+ "bulletTime, userID FROM bulletins ";
		try {
			ResultSet result = m_statement.executeQuery(sql);
			while(result.next()){
				int bulletID = result.getInt(1);
				String bulletMsg = result.getString(2);
				String bulletTime = result.getString(3);
				String userID = result.getString(4);
				m_Bulletin = new Bulletin(bulletID, bulletMsg,
						bulletTime, userID);
				returnArray.add(m_Bulletin);
			}
			
		} catch (SQLException e) {
			System.out.println("LEO LOG: findAllBulletins SQL EXECUTE FAIL \n\t" 
								+ e.getSQLState()+ "\n\t" + e.getMessage());
			System.out.println(sql);
		}
		return returnArray;
	}
	
	public ArrayList<Student> getAllStudents(){
		ArrayList<Student> returnArray = new ArrayList<Student>();
		Student m_student;
		
		String sql = "SELECT userID, userName, userPasswd, userAuth, userEmail "
				+ "FROM students";
		
		try {
			ResultSet result = m_statement.executeQuery(sql);
			while(result.next()){
				String userID = result.getString(1);
				String userName = result.getString(2);
				String userPasswd = result.getString(3);
				int userAuth = result.getInt(4);
				String userEmail = result.getString(5);
				
				m_student = new Student(userID, userName, userPasswd, userAuth, userEmail);
				returnArray.add(m_student);
			}
			
		} catch (SQLException e) {
			System.out.println("LEO LOG: findAllStudents SQL EXECUTE FAIL \n\t" 
								+ e.getSQLState()+ "\n\t" + e.getMessage());
			System.out.println(sql);
		}
		
		
		
		return returnArray;
	}
	
	
	public Student findStudentbyEmail(String userEmail){
		
		Student m_student = null;
		String sql="SELECT userID, userName, userPasswd, userAuth "
				+ "FROM students "
				+ "WHERE userEmail = '"+ userEmail + "'";
		try{
			ResultSet result = m_statement.executeQuery(sql);
			if (result.next()) {
				String userID = result.getString(1);
				String userName = result.getString(2);
				String userPasswd = result.getString(3);
				int userAuth = result.getInt(4);
				m_student = new Student(userID, userName, userPasswd, userAuth, userEmail);
			}
			
			//System.out.println(sql);
		}catch(SQLException e){
			System.out.println("LEO LOG: findStudentByEmail SQL EXECUTE FAIL \n\t" 
					+ e.getSQLState()+ "\n\t" + e.getMessage());
			//System.out.println(sql);
		}
		
		return m_student;
	}
	
	
	
	
	
	

	
}