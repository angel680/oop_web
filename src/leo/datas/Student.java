package leo.datas;

public class Student {
	
	public Student(String userID, String userName, String userPasswd, int userAuth){
		this.setUserAuth(userAuth);
		this.setUserID(userID);
		this.setUserName(userName);
		this.setUserPasswd(userPasswd);
		this.setUserEmail(null);
	}
	public Student(String userID, String userName, String userPasswd, int userAuth, String userEmail){
		this.setUserAuth(userAuth);
		this.setUserID(userID);
		this.setUserName(userName);
		this.setUserPasswd(userPasswd);
		this.setUserEmail(userEmail);
	}

	private String userID;
	private String userName; 
	private String userPasswd;
	private int userAuth;
	private String userEmail;
	public String getUserPasswd() {
		return userPasswd;
	}
	public void setUserPasswd(String userPasswd) {
		this.userPasswd = userPasswd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public int getUserAuth() {
		return userAuth;
	}
	public void setUserAuth(int userAuth) {
		this.userAuth = userAuth;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
}
