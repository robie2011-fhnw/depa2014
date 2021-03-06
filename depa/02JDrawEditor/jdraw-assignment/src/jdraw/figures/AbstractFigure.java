package jdraw.figures;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import jdraw.figures.handles.BottomLeftHandle;
import jdraw.figures.handles.BottomRightHandle;
import jdraw.figures.handles.TopLeftHandle;
import jdraw.figures.handles.TopRightHandle;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;


public abstract class AbstractFigure implements Figure, Cloneable {
	protected RectangularShape rshape;
	private List<FigureListener> observers = new ArrayList<FigureListener>();
	
	public AbstractFigure(){		
	}
	
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
	public List<FigureHandle> getHandles(){
		List<FigureHandle> handles = new LinkedList<FigureHandle>();
		handles.add(new TopRightHandle(this));
		handles.add(new TopLeftHandle(this));
		handles.add(new BottomRightHandle(this));
		handles.add(new BottomLeftHandle(this));		
		return handles;
	}


	@Override
	public AbstractFigure clone() {
		AbstractFigure clone = null;
		try {
			clone = (AbstractFigure) super.clone();
			clone.observers = new LinkedList<FigureListener>();
			
			if(rshape != null){
				clone.rshape = (RectangularShape) rshape.clone();				
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clone;
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
	
	public Point getTopRightEdge(){
		return new Point( getBounds().x + getBounds().width, 
						  getBounds().y);
	}
	
	public Point getBottomRightEdge(){
		return new Point( getBounds().x + getBounds().width, 
				   		  getBounds().y + getBounds().height);		
	}
	
	public Point getBottomLeftEdge(){
		return new Point( getBounds().x, 
				  		  getBounds().y + getBounds().height);
	}
}
