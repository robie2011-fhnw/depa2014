package jdraw.figures.handles;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.List;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

public abstract class AbstractHandle implements FigureHandle {
	protected AbstractFigure figure;
	private Dimension handleSize = new Dimension(5, 5);
	private Point startInteraction;
	
	public AbstractHandle(AbstractFigure f){
		this.figure = f;
	}

	@Override
	public Figure getOwner() {
		return this.figure;
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect( getLocation().x,
					getLocation().y, 
					handleSize.width, 
					handleSize.height);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public boolean contains(int x, int y) {
		return 	   valueInRange(x, getLocation().x, getLocation().x + handleSize.width)
				&& valueInRange(y, getLocation().y, getLocation().y+handleSize.height);
	}
	
	private boolean valueInRange(int value, int start, int end){
		return 	(value >= start) 
			 && (value <= end);
	}

	@Override
	public final void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		v.getDrawContext().showStatusText("Resizing Figure ...");
		startInteraction = new Point(x,y);
	}

	@Override
	public final void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		v.getDrawContext().showStatusText("Resizing Figure ..." +x + "/"+y);
		//if(x > 150)e.translatePoint(0, 0);
		executeInteraction( new MouseInteraction(startInteraction, new Point(x,y)) );
	}

	@Override
	public final void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		v.getDrawContext().showStatusText("");
		Point stopInteraction = new Point(x,y);
		executeInteraction( new MouseInteraction(startInteraction, stopInteraction) );
	}
	
	protected int getMinX(LinkedList<Point> points) {		
		int minX = Integer.MAX_VALUE;
		for (Point point : points)
			if (point.x < minX) minX = point.x;
		
		return minX;
	}
	
	protected int getMaxX(LinkedList<Point> points){
		int maxX = Integer.MIN_VALUE;
		for (Point point : points)
			if (point.x > maxX) maxX = point.x;
				
		return maxX;
	}
	
	protected int getMinY(LinkedList<Point> points){
		int minY = Integer.MAX_VALUE;
		for (Point point : points) 
			if (point.y < minY) minY = point.y;
				
		return minY;
	}
	
	protected int getMaxY(LinkedList<Point> points){
		int maxY = Integer.MIN_VALUE;
		for (Point point : points) 
			if (point.y > maxY) maxY = point.y;
				
		return maxY;
	}
		
	
	public abstract void executeInteraction(MouseInteraction m);
	public abstract Point getLocation();
	public abstract boolean canMoveMouseToThisPoint(Point point);
}


class MouseInteraction{
	Point start, stop;
	public MouseInteraction(Point start, Point stop){
		this.start = start;
		this.stop = stop;
	}	
	
	public Point getStart() { return start; }
	public Point getStop() {return stop; }
	
	public boolean goesLeft() 	{ return stop.x < start.x; }
	public boolean goesDown() 	{ return stop.y > start.x; }
	public boolean goesRight() 	{ return  start.x != stop.x && !goesLeft(); }
	public boolean goesUp() 	{ return start.y != stop.y && !goesDown(); }	
	
}
