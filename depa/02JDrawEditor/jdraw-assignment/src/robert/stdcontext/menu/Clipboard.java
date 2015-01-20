package robert.stdcontext.menu;

import java.util.List;

import jdraw.framework.Figure;

public class Clipboard {
	static private List<Figure> copiedFigures;
	
	public static void putInClipboard(List<Figure> newFigures){
		copiedFigures = newFigures;
	}
	
	public static List<Figure> getFromClipboard(){
		return copiedFigures;
	}
}
