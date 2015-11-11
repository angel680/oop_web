package leo;

import java.sql.*;
import java.util.ArrayList;

import leo.datas.*;

public class DataAccess {
	
	static Connection m_connet = null;
	static Statement m_statement = null;
	static String url = "jdbc:sqlite:";
	
	public static Connection init(String path){
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
	
	public static Student findStudent(String userID){
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

	public static Bulletin findBulletin(int bulletID){
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
		}
		
		return m_bulletin;
	}
	
	public static Comment findComment(int commentID){
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
	
	public static Favor findFavor(int favorID){
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
	
	public static ArrayList<Comment> findCommentsByUser(String userID){
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
			System.out.println("LEO LOG: findCommentsByUser SQL EXECUTE FAIL");
		}	
		return returnArray;
	}
	
	public static ArrayList<Bulletin> findBulletinByUser(String userID){
		ArrayList<Bulletin> returnArray = new ArrayList<Bulletin>();
		Bulletin m_Bulletin;
		
		String sql = "SELECT bulletID, bulletMsg, "
				+ "bulletTime FROM bulletins "
				+ "WHRER userID = '" + userID + "'";
		
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
			System.out.println("LEO LOG: findBulletinByUser SQL EXECUTE FAIL");
		}
		
		return returnArray;
	}

	public static ArrayList<Favor> findFavorByUser(String userID){
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
			System.out.println("LEO LOG: findFavorByUser SQL EXECUTE FAIL");			e.printStackTrace();
		}		
		return returnArray;
	}
	
	public static boolean addStudent(Student student){
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
					+ "\t" + e.getSQLState());
		}
		return false;
	}
	
	public static boolean addBulletin(Bulletin bulletin){
		String sql = "INSERT INTO bulletins "
				+ "(bulletID, bulletMsg, bulletTime, userID) "
				+ "VALUES("
					+ String.valueOf(bulletin.getBulletID())+ ", '"
					+ bulletin.getBulletMsg() + "', '"
					+ bulletin.getBulletTime() + "', '"
					+ bulletin.getUserID() 
				+ "')";
		try {
			m_statement.executeUpdate(sql);
			return true;
		} catch (SQLException e) {
			System.out.println("LEO LOG: addBulletin SQL EXECUTE FAIL: \n"
					+ "\t" + e.getSQLState());			
		}
		return false;
	}
	
	public static boolean addComment(Comment comment){
		String sql = "INSERT INTO comments "
				+ "(commentID, commentMsg, "
				+ "commentTime, userID, bulletID)"
				+ "VALUES("
					+ String.valueOf(comment.getCommentID()) + ", '"
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
		}
		return false;
	}
	
	public static boolean addFavor(Favor favor){
		String sql = "INSERT INTO favors"
				+ "(favorID, favorTime, userID, bulletID)"
				+ "VALUES("
					+ String.valueOf(favor.getFavorID()) + ", '"
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
	
}