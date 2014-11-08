package jdraw.figures.handles;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

public abstract class AbstractHandle implements FigureHandle {
	protected AbstractFigure figure;
	protected Dimension offset;
	private Dimension handleSize = new Dimension(5, 5);
	
	public AbstractHandle(AbstractFigure f, int offsetX, int offsetY){
		this.figure = f;
		this.offset = new Dimension(offsetX, offsetY);
	}

	@Override
	public Figure getOwner() {
		return this.figure;
	}

	@Override
	public Point getLocation() {
		Point point = figure.getLocation();
		Point handleLocation = new Point(point.x + offset.width, point.y + offset.height);
		return handleLocation;
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(getLocation().x, getLocation().y, handleSize.width, handleSize.height);
	}

	@Override
	public Cursor getCursor() {
		return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
	}

	@Override
	public boolean contains(int x, int y) {
		return valueInRange(x, getLocation().x, getLocation().x + handleSize.width)
				&& valueInRange(y, getLocation().y, getLocation().y+handleSize.height);
	}
	
	private boolean valueInRange(int value, int start, int end){
		return value >= start && value <= end;
	}

	@Override
	public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
		System.out.printf("startInteraction %d/%d\r\n", x,y);
		// TODO Auto-generated method stub
	}

	@Override
	public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
		// TODO Auto-generated method stub
		System.out.printf("dragInteraction %d/%d\r\n", x,y);
	}

	@Override
	public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
		// TODO Auto-generated method stub
		System.out.printf("stopInteraction %d/%d\r\n", x,y);
	}

}
