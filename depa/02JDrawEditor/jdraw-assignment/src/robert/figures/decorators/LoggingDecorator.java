package robert.figures.decorators;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public class LoggingDecorator extends AbstractDecorator {	
	public LoggingDecorator(DrawContext drawContext, Figure f){
		super(drawContext,f);
	}	

	public void draw(Graphics g) {
		log("draw()");
		super.draw(g);
	}

	public void move(int dx, int dy) {
		log( String.format("move(%d,%d)",dx,dy));
		super.move(dx, dy);
	}

	public boolean contains(int x, int y) {
		log( String.format("contains(%d,%d)",x,y));
		return super.contains(x, y);
	}

	public void setBounds(Point origin, Point corner) {
		log( String.format("setBounds()"));
		super.setBounds(origin, corner);
	}

	public Rectangle getBounds() {
		log( "getBounds()" );
		return super.getBounds();
	}

	public List<FigureHandle> getHandles() {
		log( "getHandles()" );
		return super.getHandles();
	}

	public void addFigureListener(FigureListener listener) {
		log( "addFigureListeners()" );
		super.addFigureListener(listener);
	}

	public void removeFigureListener(FigureListener listener) {
		log( "removeFigureListeners()" );
		super.removeFigureListener(listener);
	}

	public LoggingDecorator clone() {
		log( "clone()" );
		return (LoggingDecorator) super.clone();
	}
	
	private void log(String str){
		System.out.println(str);
	}
}
