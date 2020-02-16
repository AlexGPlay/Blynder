package atrahasis.core.chromium.handler;

public interface AppHandlerObservable {

	public void addObserver(AppHandlerObserver observer);
	public void notifyObservers(String data);
	
}
