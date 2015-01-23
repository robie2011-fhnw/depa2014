package robert.stdcontext;

import java.util.LinkedList;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import jdraw.framework.DrawContext;

public class SelectionChangedListener {
	static DrawContext drawContext;
	static LinkedList<Observer> observers = new LinkedList<Observer>();
	
	private SelectionChangedListener(){}
	
	public static void update(){
		for(Observer o : observers) o.update();
		
		System.out.println("Selected Figures: " +drawContext.getView().getSelection().size());
	}
	
	public static void registerForChanges(Observer menuItem){
		observers.add(menuItem);
	}
}
