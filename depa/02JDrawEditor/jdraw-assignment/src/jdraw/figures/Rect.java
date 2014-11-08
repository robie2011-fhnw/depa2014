package jdraw.figures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;
import java.util.List;

import jdraw.framework.FigureHandle;

public class Rect extends AbstractFigure {

	public Rect(int x, int y, int w, int h) {
		super( new Rectangle(x,y,w,h));
	}

	@Override
	public void draw(Graphics g) {
		
		int x = (int) rshape.getX();
		int y = (int) rshape.getY();
		int w = (int) rshape.getWidth();
		int h = (int) rshape.getHeight();
		
		g.setColor(Color.WHITE);
		g.fillRect(x,y,w,h);
		g.setColor(Color.BLACK);
		g.drawRect(x,y,w,h);
	}

	@Override
	public List<FigureHandle> getHandles() {
		// TODO Auto-generated method stub
		return null;
	}

}
