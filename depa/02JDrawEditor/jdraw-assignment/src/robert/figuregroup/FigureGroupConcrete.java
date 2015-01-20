package robert.figuregroup;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import jdraw.figures.AbstractFigure;
import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import jdraw.framework.FigureGroup;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

interface FigureAction{ void execute(Figure f); }

public class FigureGroupConcrete extends AbstractFigure implements FigureGroup, Figure {
	private LinkedList<Figure> figures;
	private Rectangle bounds;
	private DrawContext context;

	public FigureGroupConcrete(DrawContext context) {
		this.context = context;
		List<Figure> selectedFigures = context.getView().getSelection();
		
		if (selectedFigures.size()<2) return;		
		figures = new LinkedList<Figure>(selectedFigures);
		
		for(Figure f : selectedFigures){			
			context.getModel().removeFigure(f);
		}

		context.getModel().addFigure(this);
		context.getView().addToSelection(this);
	}

	private void executeActionOverFigureParts(FigureAction action){
		for(Figure f : figures)
			action.execute(f);
	}
	
	@Override
	public Iterable<Figure> getFigureParts() {		
		return this.figures;
	}

	@Override
	public void draw(final Graphics g) {
		executeActionOverFigureParts(new FigureAction() {			
			@Override
			public void execute(Figure f) {
				f.draw(g);				
			}
		});
	}

	@Override
	public void move(final int dx, final int dy) {
		bounds = null;
		executeActionOverFigureParts(new FigureAction() {
			
			@Override
			public void execute(Figure f) {				
				f.move(dx, dy);
			}
		});
	}

	@Override
	public Point getLocation() {
		return getBounds().getLocation();
	}
	
	@Override
	public boolean contains(int x, int y) {		
		return getBounds().contains(x, y);
	}

	@Override
	public void setBounds(Point origin, Point corner) {
		bounds = null;
		// TODO Auto-generated method stub		
	}

	@Override
	public Rectangle getBounds() {
		if(bounds != null) return bounds;
		
		final Rectangle minRectangle = figures.get(0).getBounds();
		executeActionOverFigureParts(new FigureAction() {
			
			@Override
			public void execute(Figure f) {
				minRectangle.add(f.getBounds());				
			}
		});
		
		bounds = minRectangle;
		
		return bounds;
	}

	@Override
	public FigureGroupConcrete clone() {
		final FigureGroupConcrete clone = (FigureGroupConcrete) super.clone();
		clone.bounds = null;
		clone.figures = new LinkedList<Figure>();
		
		executeActionOverFigureParts(new FigureAction() {
			
			@Override
			public void execute(Figure f) {
				clone.figures.add(f.clone());
			}
		});
		
		return clone;
	}
	
	public void ungroup(){
		System.out.println("call ungroup");
		final DrawContext ctx = this.context;
		executeActionOverFigureParts(new FigureAction() {
			
			@Override
			public void execute(Figure f) {
				ctx.getModel().addFigure(f);
				ctx.getView().addToSelection(f);
			}
		});
		
		ctx.getModel().removeFigure(this);
		this.figures = null;
	}

}
