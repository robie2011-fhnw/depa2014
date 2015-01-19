package robert.pointconstrainer;

import java.awt.Point;

import jdraw.framework.PointConstrainer;

public class FivePixelPointConstrainer implements PointConstrainer {

	public FivePixelPointConstrainer() {
	}

	@Override
	public Point constrainPoint(Point p) {
		Point newPoint = new Point();
		int diffX = p.x % 5;
		int diffY = p.y % 5;
		
		newPoint.x = diffX < 3 ? p.x - diffX : p.x + (5-diffX);
		newPoint.y = diffY < 3 ? p.y - diffY : p.y + (5-diffY);
		
		return newPoint;
	}

	@Override
	public int getStepX(boolean right) {
		return right ? 5 : -5;
	}

	@Override
	public int getStepY(boolean down) {
		return down ? 5 : -5;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDown() {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseUp() {
		// TODO Auto-generated method stub

	}

}
