package jdraw.figures;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

import jdraw.figures.handles.AbstractHandle;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

public class Oval extends AbstractFigure {

	public Oval(int x, int y, int w, int h) {
		super(new Ellipse2D.Double(x,y,w,h));
	}

	@Override
	public void draw(Graphics g) {
		int x = (int) rshape.getX();
		int y = (int) rshape.getY();
		int w = (int) rshape.getWidth();
		int h = (int) rshape.getHeight();
		
		g.setColor(Color.WHITE);
		g.fillOval(x,y,w,h);
		g.setColor(Color.BLACK);
		g.drawOval(x,y,w,h);
	}

	@Override
	public List<FigureHandle> getHandles() {
		List<FigureHandle> handles = new ArrayList<FigureHandle>();
		
		final AbstractFigure figurOben = this;
		
		handles.add( new AbstractHandle(figurOben, 0,0) {
			
		});
		// TODO Auto-generated method stub
		return handles;
	}

}
