package jdraw.figures;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;


public abstract class AbstractFigure implements Figure {
	protected RectangularShape rshape;
	private List<FigureListener> observers = new ArrayList<FigureListener>();
	
	public AbstractFigure(RectangularShape r) {
		this.rshape = r;
	}

	@Override
	public abstract void draw(Graphics g);
	

	@Override
	public void move(int dx, int dy) {
		if(dx == 0 && dy == 0) return; // nothing changed
		
		Point2D newLocation = new Point(getBounds().x+dx, getBounds().y+dy);
		Dimension oldSize = new Dimension(getBounds().width, getBounds().height);
		rshape.setFrame(newLocation, oldSize);
		notifyObservers();
	}

	@Override
	public boolean contains(int x, int y) {
		return rshape.contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		rshape.setFrameFromDiagonal(origin, corner);
		notifyObservers();
	}

	@Override
	public Rectangle getBounds() {
		return rshape.getBounds();
	}
	
	public Point getLocation(){
		Point p = new Point();
		p.x = (int) rshape.getX();
		p.y = (int) rshape.getY();
		return p;
	}


	/**
	 * Returns a list of 8 handles for this Rectangle.
	 * @return all handles that are attached to the targeted figure.
	 * @see jdraw.framework.Figure#getHandles()
	 */	
	@Override
	public abstract List<FigureHandle> getHandles();


	@Override
	public Figure clone() {
		// TODO Auto-generated method stub
		return null;
	}

	private void notifyObservers(){
		for (FigureListener figureListener : observers) {
			figureListener.figureChanged(new FigureEvent(this));
		}
		
	}
	@Override
	public void addFigureListener(FigureListener listener) {
		if(!observers.contains(listener)){
			observers.add(listener);			
		}
	}

	@Override
	public void removeFigureListener(FigureListener listener) {
		observers.remove(listener);
	}
}
