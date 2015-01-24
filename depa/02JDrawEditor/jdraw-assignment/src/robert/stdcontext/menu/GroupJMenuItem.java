package robert.stdcontext.menu;

import java.util.List;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import robert.figuregroup.FigureGroupConcrete;
import robert.stdcontext.Observer;
import robert.stdcontext.SelectionChangedListener;

public class GroupJMenuItem extends DefaultJMenuItem implements Observer {

	public GroupJMenuItem(final DrawContext drawContext) {
		super(drawContext,"Group");
		setEnabled(false);
		SelectionChangedListener.registerForChanges(this);
	}

	@Override
	public void update() {
		setEnabled(drawContext.getView().getSelection().size()>1);
	}

	@Override
	void callOnActionPerformed() {
		List<Figure> selectedFigures = drawContext.getView().getSelection();
		if(selectedFigures instanceof FigureGroupConcrete == false) {					
			new FigureGroupConcrete(drawContext.getView().getDrawContext());
		}		
		
	}
}
