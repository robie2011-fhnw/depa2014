package robert.figures.decorators;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.List;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

public abstract class AbstractDecorator implements Figure {
	Figure figure;
	DrawContext drawContext;
	
	// Cloning
	public AbstractDecorator(AbstractDecorator original){
		figure = original.clone();
	}
	
	public AbstractDecorator(DrawContext drawContext, Figure f){
		this.figure = f;
		this.drawContext = drawContext;
		drawContext.getModel().removeFigure(f);
		drawContext.getModel().addFigure(this);
	}
	
	public void restoreOriginal(){
		drawContext.getModel().removeFigure(this);
		drawContext.getModel().addFigure(this.figure);
	}
	
	public void draw(Graphics g) {
		figure.draw(g);
	}

	public void move(int dx, int dy) {
		figure.move(dx, dy);
	}

	public boolean contains(int x, int y) {
		return figure.contains(x, y);
	}

	public void setBounds(Point origin, Point corner) {
		figure.setBounds(origin, corner);
	}

	public Rectangle getBounds() {
		return figure.getBounds();
	}

	public List<FigureHandle> getHandles() {
		return figure.getHandles();
	}

	public void addFigureListener(FigureListener listener) {
		figure.addFigureListener(listener);
	}

	public void removeFigureListener(FigureListener listener) {
		figure.removeFigureListener(listener);
	}

	public AbstractDecorator clone() {
		AbstractDecorator clone = null;
		try {
			clone = (AbstractDecorator) super.clone();
			clone.figure = figure.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return clone;
	}

}
