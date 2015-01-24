package robert.stdcontext.menu;

import java.util.List;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import robert.figuregroup.FigureGroupConcrete;
import robert.stdcontext.Observer;

public class UngroupJMenuItem extends DefaultJMenuItem implements Observer {

	public UngroupJMenuItem(final DrawContext drawContext) {
		super(drawContext, "Ungroup");
		setEnabled(false);
	}

	@Override
	public void update() {
		boolean enabled=false;
		for(Figure f : drawContext.getView().getSelection())
			if(f instanceof FigureGroupConcrete)enabled=true;
		
		setEnabled(enabled);
		
	}

	@Override
	void callOnActionPerformed() {
		List<Figure> selectedFigures = drawContext.getView().getSelection();
		for(Figure f : selectedFigures){
			if(f instanceof FigureGroupConcrete)
				((FigureGroupConcrete) f).ungroup();					
		}
	}
}
