package notification;
import java.util.ArrayList;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

public class User implements Observer{
	String userid[];
	private final Object MONITOR = new Object();
	//Logger logger = LogManager.getLogger(User.class.getName()); 
	  
	
	public User(String[] userid)
	{
		//logger.traceEntry();
		this.userid = userid;
		//logger.trace("New User Created");
		//logger.traceExit();
	}
	public void checknotif(ArrayList<Notification> n)
	{
		//logger.traceEntry();
		synchronized(MONITOR) {
		for(int j=0; j<userid.length;j++)
		{
			for(int i=0; i<n.size();i++)
			{
				int k=0;
				if(userid[j].compareTo(n.get(i).userid[k]) == 0)
				{
					if(n.get(i).notiftype[k] == "Follow")
					{
						n.get(i).content[k] = n.get(i).notiftyperef[k] + " followed you.";
					}
					else if(n.get(i).notiftype[k] == "Tagged_Post")
					{
						n.get(i).content[k] = n.get(i).notiftyperef[k] + " tagged you in post, " + n.get(i).postid[k];
					}
					else if(n.get(i).notiftype[k] == "Tagged_Comment")
					{
						n.get(i).content[k] = n.get(i).notiftyperef[k] + " tagged you in a comment in post, " + n.get(i).postid[k];
					}
					else if(n.get(i).notiftype[k] == "Liked_Post")
					{
						n.get(i).content[k] = n.get(i).notiftyperef[k] + " liked your post, " + n.get(i).postid[k];
					}
					else if(n.get(i).notiftype[k] == "Liked_Comment")
					{
						n.get(i).content[k] = n.get(i).notiftyperef[k] + " liked your comment in post, " + n.get(i).postid[k];
					}
					else
					{
						n.get(i).content[k] = n.get(i).notiftyperef[k] + " commented on your post, " + n.get(i).postid[k];
					}
				//System.out.println(userid[j] + ", Name: " +n.get(i).username + ", Notification Type: " + n.get(i).notiftype + ", Content : "+ n.get(i).content);
				//logger.trace("User checks for notifications");
				}
			}
		}
		}
		//logger.traceExit();
	}
	@Override
	public void update(Application o) {
		//logger.traceEntry();
		synchronized(MONITOR) {
		if(o instanceof Application) {
			Application ap = (Application) o;
			//logger.trace("User gets updates from application");
			checknotif(ap.getState());
			//logger.traceExit();
		}
		}
	}

}
