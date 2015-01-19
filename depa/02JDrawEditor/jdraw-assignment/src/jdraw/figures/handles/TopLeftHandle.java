package jdraw.figures.handles;

import java.awt.Point;
import jdraw.figures.AbstractFigure;

public class TopLeftHandle extends AbstractHandle {

	public TopLeftHandle(AbstractFigure f) {
		super(f);
	}

	@Override
	public void executeInteraction(MouseInteraction interaction) {
		Point newOrigin = interaction.stop;
		Point newCorner = figure.getBottomRightEdge();
		figure.setBounds(newOrigin, newCorner);
	}

	@Override
	public Point getLocation() {
		return this.figure.getLocation();
	}
}
