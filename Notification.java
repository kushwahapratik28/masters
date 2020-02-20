package notification;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class Notification {

	String userid[];
	String username[];
	String notiftype[];
	String content [];
	String notiftyperef[];
	String postid[];
	//Logger logger = LogManager.getLogger(Notification.class.getName());
	
	public Notification(String[] userid, String username[], String notiftype[], String notiftyperef[], String postid[], String content[])
	{
		//logger.traceEntry();
		this.userid = userid;
		this.username = username;
		this.notiftype = notiftype;
		this.content = content;
		this.notiftyperef =notiftyperef;
		this.postid = postid;

	    //logger.trace("New notification received");
	    //logger.traceExit();
	}
}
