package notification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
	private static final Logger logger = LogManager.getLogger();
	public static void main(String[] args) throws InterruptedException {
		Application app = new Application();
		
		//user data - set of active listeners
		String[] userid = {"A1","A2", "A3"};
		User u = new User(userid);
		
		//notification content - notification database
		String[] mainusers = {userid[1], userid[0], userid[2], userid[2], userid[1], userid[0]};
		String[] usernames = {"Paul Simon","Nick Simon", "Peter Griffin", "Peter Griffin", "Paul Simon", "Nick Simon"};
		String[] notiftype = {"Follow", "Tagged_Post","Tagged_Comment","Liked_Post","Liked_Comment","Comment"};
		String[] refusers = {userid[0], userid[2], userid[0], userid[0], userid[2], userid[2]}; 
		String[] postid = {"","P101", "P112","P113", "P222", "P101"};
		String[] content = {"","", "", "", "", ""};
		Notification n = new Notification(mainusers, usernames, notiftype, refusers, postid, content);
		
		for(int i=0; i<n.userid.length; i++) {
			app.addnotif(n, u);
		}

		System.out.println("--------------FINISHED-CHECK LOGS-------------");
	}
	
	
	
	//ASPECTJ code
	public static aspect LogAspect {

		String callerID = null;
		String calleeID = null;
		int id  = 0;
		pointcut publicMethodExecute(): call(* addnotif(..)) && !within(LogAspect);
		
		before(): publicMethodExecute() {
			callerID = getId(thisEnclosingJoinPointStaticPart);
			calleeID = getId(thisJoinPointStaticPart);
			id +=1;
			System.out.println(callerID + ":" + calleeID + " :" + id);
		}
			
		
		pointcut publicMethodCall(): execution(* *(..)) && !within(LogAspect) && !within(Main);

		before(): publicMethodCall() {
			
			System.out.printf("Enters on method: %s %s%s. \n", thisJoinPointStaticPart.getSignature(), calleeID, id);
			System.out.printf("Caller method: %s %s%s. \n", thisEnclosingJoinPointStaticPart.getSignature().getDeclaringTypeName(), callerID, id);
			logger.traceEntry(thisEnclosingJoinPointStaticPart.getSignature().getDeclaringTypeName() + "; " + thisJoinPointStaticPart.getSignature() + calleeID + id +"; "+ thisJoinPoint.getSignature());
		}
		after() returning: publicMethodCall() {
			//String callerID = getId(thisEnclosingJoinPointStaticPart);
			//String calleeID = getId(thisJoinPointStaticPart);   
		    System.out.printf("Exits method: %s %s%s. \n", thisJoinPointStaticPart.getSignature(), calleeID, id);
		    logger.trace(thisEnclosingJoinPointStaticPart.getSignature().getDeclaringTypeName() + "; " + thisJoinPointStaticPart.getSignature() + calleeID+id +"; "+ thisJoinPoint.getSignature());
		}
		//id generator
		private static String getId(Object subject) {
			// "Returns null when there is no currently executing object available.
			// This includes all join points that occur in a static context."
			int psuedoId = 0;
			boolean psuedoIdIsSet = false;

			if (subject != null) {
				psuedoId = System.identityHashCode(subject);
				psuedoIdIsSet = true;
			}

			String id = "Static";
			if (psuedoIdIsSet) {
				id = "_ID_" + psuedoId;
			}

			return id;
		}
	}
	
	
	
}

