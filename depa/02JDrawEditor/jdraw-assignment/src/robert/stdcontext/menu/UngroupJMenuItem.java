package robert.stdcontext.menu;

import java.util.List;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import jdraw.framework.FigureGroup;
import robert.figuregroup.FigureGroupConcrete;
import robert.stdcontext.Observer;
import robert.stdcontext.SelectionChangedListener;

public class UngroupJMenuItem extends DefaultJMenuItem implements Observer {

	public UngroupJMenuItem(final DrawContext drawContext) {
		super(drawContext, "Ungroup");
		setEnabled(false);
		SelectionChangedListener.registerForChanges(this);
	}

	@Override
	public void update() {
		boolean enabled=false;
		for(Figure f : drawContext.getView().getSelection())
			if(f instanceof FigureGroup)enabled=true;
		
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
