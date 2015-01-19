package jdraw.figures.handles;

import java.awt.Point;

import jdraw.figures.AbstractFigure;

public class BottomLeftHandle extends AbstractHandle {

	public BottomLeftHandle(AbstractFigure f) {
		super(f);
	}

	@Override
	public void executeInteraction(MouseInteraction m) {
		Point newOrigin = figure.getLocation();
		newOrigin.x = m.stop.x;
		
		Point newCorner = figure.getBottomRightEdge();
		newCorner.y = m.stop.y;
		
		figure.setBounds(newOrigin, newCorner);
	}

	@Override
	public Point getLocation() {
		return figure.getBottomLeftEdge();
	}
}
