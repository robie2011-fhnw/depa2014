package robert.stdcontext.menu;

import java.util.LinkedList;
import java.util.List;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

public class SendToBackJMenuItem extends DefaultJMenuItem {
	public SendToBackJMenuItem(DrawContext drawContext){
		super(drawContext, "Send to back", null);
	}
	
	
	/**
	 * Changes the order of figures and moves the figures in the selection
	 * to the back, i.e. moves them to the front of the list of figures.
	 * @param model model in which the order has to be changed
	 * @param selection selection which is moved to the back
	 */
	private void sendToBack(DrawModel model, List<Figure> selection) {
		// the figures in the selection are ordered according to the order in
		// the model
		List<Figure> orderedSelection = new LinkedList<Figure>();
		for (Figure f : model.getFigures()) {
			if (selection.contains(f)) {
				orderedSelection.add(f);
			}
		}
		int pos = 0;
		for (Figure f : orderedSelection) {
			model.setFigureIndex(f, pos++);
		}
	}


	@Override
	void callOnActionPerformed(DrawContext drawContex) {
		sendToBack(drawContex.getView().getModel(), drawContex.getView().getSelection());
		drawContex.getView().repaint();
	}
}
