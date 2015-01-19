package jdraw.figures.handles;

import java.awt.Point;

import jdraw.figures.AbstractFigure;

public class TopRightHandle extends AbstractHandle {

	public TopRightHandle(AbstractFigure f) {
		super(f);
	}

	@Override
	public void executeInteraction(MouseInteraction m) {
		Point newOrigin = figure.getLocation();
		newOrigin.y = m.stop.y;
		
		Point newCorner = figure.getBottomRightEdge();
		newCorner.x = m.stop.x;
		
		figure.setBounds(newOrigin, newCorner);
	}

	@Override
	public Point getLocation() {		
		return figure.getTopRightEdge();
	}

	@Override
	public boolean canMoveMouseToThisPoint(Point point) {
		// TODO Auto-generated method stub
		return false;
	}

}
