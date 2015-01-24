package robert.stdcontext.menu;

import java.util.List;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import robert.figures.decorators.LoggingDecorator;
import robert.stdcontext.Observer;
import robert.stdcontext.SelectionChangedListener;

public class LogDecoratorJMenuItem extends DefaultJMenuItem implements Observer {

	public LogDecoratorJMenuItem(DrawContext drawContext) {
		super(drawContext, "Figure Logging (Decorator) On/Off");
		setEnabled(false);
		SelectionChangedListener.registerForChanges(this);
	}

	@Override
	void callOnActionPerformed() {		
		List<Figure> selectedFigures = drawContext.getView().getSelection();
		for(Figure figure : selectedFigures){
			if(figure instanceof LoggingDecorator){
				((LoggingDecorator) figure).restoreOriginal();
			}else{			
				new LoggingDecorator(drawContext, figure);
			}
		}
	}

	@Override
	public void update() {
		setEnabled(drawContext.getView().getSelection().size()>0);
	}

}
