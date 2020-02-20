package notification;
import java.util.ArrayList;
import java.util.Random;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;


public class Application implements Subject{
	private ArrayList<Notification> allNotification;
	private ArrayList<Observer> observers;
	private Random random;
	int rand;
	private final Object MONITOR = new Object();
	//private static final Logger logger = LogManager.getLogger(Application.class.getName());
      
  
	public Application()
	{
		allNotification = new ArrayList<>();
		observers = new ArrayList<>();
		random = new Random();
		rand = random.nextInt(1000);
	}
	public void addnotif(Notification n, Observer o) throws InterruptedException
	{
		//logger.traceEntry();
		synchronized(MONITOR) {
			
			allNotification.add(n);

	        //attach and notify listeners
			Attach(o);
			Notify();
			
			//logger.debug("Sleep");
			
			Thread.sleep(rand);
			
								
			//logger.debug("Wake");
			
	        rand += 500;
			if(rand < 5000)
			{
				Thread.sleep(rand);
				
				//Detach randomly
				Deattach(o);
				rand -= 500;
			}
		//logger.traceExit();
		}	
	}
	public synchronized ArrayList<Notification> getState()
	{
		//logger.traceEntry();
		//logger.trace("Get notifications");
		//logger.traceExit();
		
		return allNotification;
	}
	public void Attach(Observer o) throws InterruptedException
	{
		//logger.traceEntry();
		
		if (o == null) return;
		synchronized(MONITOR) {
		if(!observers.contains(o)) {
			
			observers.add(o);
		}
		}
		
	}
	public void Deattach(Observer o)
	{
		//logger.traceEntry();
		if (o == null) return;
		synchronized(MONITOR) {
		if(observers.contains(o) && o!= null);{
		
			observers.remove(o);
			//logger.trace("Observer/user detached");
		}
		//logger.traceExit();
		}
	}
	public void Notify()
	{
		//logger.traceEntry();
		synchronized(MONITOR) {
			if (observers.size() == 0) return;
			for(int i=0; i< observers.size();i++)
			{
				//logger.trace("Notify observer/user");
				(observers.get(i)).update(this);
								
			}
		}
		//logger.traceExit();
	}
}
