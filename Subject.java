package notification;

public interface Subject {

	void Attach (Observer o) throws InterruptedException;
	void Deattach (Observer o);
	void Notify();
}
