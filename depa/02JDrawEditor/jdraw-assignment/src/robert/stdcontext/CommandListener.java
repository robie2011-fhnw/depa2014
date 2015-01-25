package robert.stdcontext;

import java.util.LinkedList;

public class CommandListener {
	static LinkedList<Observer> observers = new LinkedList<Observer>();
	
	public static void sendUpdate(){
		for(Observer observer : observers) observer.update();
	}
	
	public static void addObserver(Observer o){
		observers.add(o);
	}
}
