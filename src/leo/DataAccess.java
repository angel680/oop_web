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
			boolean got = result.next();
			if (got) {
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
			System.out.println("LEO LOG: findStudent SQL EXECUTE FAIL");
		}
		
		return m_student;
	}

	public static ArrayList<Comment> findCommentsByUser(String userID){
		ArrayList<Comment> returnArray = new ArrayList<Comment>();
		Comment m_comment;
		
		String sql = "SELECT commentID, commentMsg, "
				+ "commentTime, bulletID FORM comments "
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
	
	
}