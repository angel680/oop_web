package model;

public class Bulletin {
	private int bulletID;
	private String bulletTitle;
	private String bulletMsg;
	private String bulletTime;
	private String userID;
	
	public Bulletin(int bulletID,String bulletTitle, String bulletMsg, String bulletTime, String userID){
		setBulletID(bulletID);
		setBulletTitle(bulletTitle);
		setBulletMsg(bulletMsg);
		setBulletTime(bulletTime);
		setUserID(userID);
	}
	
	
	public int getBulletID() {
		return bulletID;
	}
	public void setBulletID(int bulletID) {
		this.bulletID = bulletID;
	}
	public String getBulletMsg() {
		return bulletMsg;
	}
	public void setBulletMsg(String bulletMsg) {
		this.bulletMsg = bulletMsg;
	}
	public String getBulletTime() {
		return bulletTime;
	}
	public void setBulletTime(String bulletTime) {
		this.bulletTime = bulletTime;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}


	public String getBulletTitle() {
		return bulletTitle;
	}


	public void setBulletTitle(String bulletTitle) {
		this.bulletTitle = bulletTitle;
	}
}
