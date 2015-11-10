package leo.datas;

public class Comment {
	private int commentID;
	private String commentMsg;
	private String commentTime;
	private String userID;
	private int bulletID;
	
	public Comment(int commentID, String commentMsg,
				String commentTime, String userID,
				int bulletID){
		this.setCommentID(commentID);
		this.setCommentMsg(commentMsg);
		this.setCommentTime(commentTime);
		this.setUserID(userID);
		this.setBulletID(bulletID);
	}
	
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getCommentMsg() {
		return commentMsg;
	}
	public void setCommentMsg(String commentMsg) {
		this.commentMsg = commentMsg;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(String commentTime) {
		this.commentTime = commentTime;
	}
	public int getBulletID() {
		return bulletID;
	}
	public void setBulletID(int bulletID) {
		this.bulletID = bulletID;
	}
	
}
