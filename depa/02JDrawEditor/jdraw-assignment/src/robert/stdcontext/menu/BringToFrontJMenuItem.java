package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JMenuItem;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

public class BringToFrontJMenuItem extends JMenuItem {
	public BringToFrontJMenuItem(final DrawContext drawContex){
		super("Bring To Front");
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bringToFront(drawContex.getView().getModel(), drawContex.getView().getSelection());
				drawContex.getView().repaint();
			}
		});
	}
	
	/**
	 * Changes the order of figures and moves the figures in the selection
	 * to the front, i.e. moves them to the end of the list of figures.
	 * @param model model in which the order has to be changed
	 * @param selection selection which is moved to front
	 */
	private void bringToFront(DrawModel model, List<Figure> selection) {
		// the figures in the selection are ordered according to the order in
		// the model
		List<Figure> orderedSelection = new LinkedList<Figure>();
		int pos = 0;
		for (Figure f : model.getFigures()) {
			pos++;
			if (selection.contains(f)) {
				orderedSelection.add(0, f);
			}
		}
		for (Figure f : orderedSelection) {
			model.setFigureIndex(f, --pos);
		}
	}
}
