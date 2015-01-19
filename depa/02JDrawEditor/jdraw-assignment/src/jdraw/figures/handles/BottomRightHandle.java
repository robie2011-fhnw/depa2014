package jdraw.figures.handles;

import java.awt.Point;

import jdraw.figures.AbstractFigure;

public class BottomRightHandle extends AbstractHandle {

	public BottomRightHandle(AbstractFigure f) {
		super(f);
	}

	@Override
	public void executeInteraction(MouseInteraction m) {
		Point origin = figure.getLocation();		
		Point newCorner = m.stop;
		
		figure.setBounds(origin, newCorner);
	}

	@Override
	public Point getLocation() {
		return figure.getBottomRightEdge();
	}
}
