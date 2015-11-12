package model;

public class Favor {
	private int favorID;
	private String favorTime;
	private String userID;
	private int bulletID;
	
	public Favor(int favorID, String favorTime,
			String userID, int bulletID){
		setBulletID(bulletID);
		setFavorID(favorID);
		setUserID(userID);
		setFavorTime(favorTime);
	}
	
	public int getFavorID() {
		return favorID;
	}
	public void setFavorID(int favorID) {
		this.favorID = favorID;
	}
	public String getFavorTime() {
		return favorTime;
	}
	public void setFavorTime(String favorTime) {
		this.favorTime = favorTime;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getBulletID() {
		return bulletID;
	}
	public void setBulletID(int bulletID) {
		this.bulletID = bulletID;
	}
}
