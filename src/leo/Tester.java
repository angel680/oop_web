package leo;
import leo.datas.*;
public class Tester {

	public static void main(String[] args) {

		DataAccess.init("dd.db");
		//DataAccess.updateStudent(new Student("U201417137","new", "passwd", 1, "qq@qq.com"));
		//DataAccess.updateComments(new Comment(2, "new", "then", "U201417136", 1));
		//DataAccess.updateBulletin(new Bulletin(1, "sdfsafs", "dsaf", "U201417136"));
		
		//DataAccess.addFavor(new Favor(0, "sds","U201417137" , 2));
		//DataAccess.addStudent(new Student("U201417136", "sdaf", "asdf", 1));
		//DataAccess.addComment(new Comment(0, "balabla", "Now", "U201417138", 1));
		//DataAccess.addBulletin(new Bulletin(3, "sadasd", "232", "U201417138"));
		
		//DataAccess.deleteComment(1);
		//DataAccess.deleteFavor(new Favor(3, "sds","U201417138" , 2));
		//DataAccess.deleteFavor("U201417138" , 1);
		//DataAccess.deleteBulletin(2);
		
		//System.out.println(DataAccess.findStudent("U201417138").getUserEmail());
		//System.out.println(DataAccess.findFavor(12).getUserID());
		//System.out.println(DataAccess.findComment(3).getCommentMsg());
		//System.out.println(DataAccess.findBulletin(1).getBulletMsg());
		
		
		//System.out.println(DataAccess.findFavorsByUser("U201417137").size());
		//System.out.println(DataAccess.findCommentsByUser("U201417138").get(0).getCommentMsg());
		//System.out.println(DataAccess.findBulletinsByUser("U201417136").get(0).getBulletMsg());
		
		//System.out.println(DataAccess.findCommentByBulletin(1).get(1).getCommentMsg());
		//System.out.println(DataAccess.findFavorByBulletin(1).get(0).getUserID());
		
		
		
		
		
		DataAccess.terminate();
	}

}
