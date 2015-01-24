package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import robert.stdcontext.SelectionChangedListener;

public class SelectAllJMenuItem extends JMenuItem {
	SelectionChangedListener listener;

	public SelectAllJMenuItem(final DrawContext drawContext){
		super("Select All");
		setEnabled(true);
		setAccelerator(KeyStroke.getKeyStroke("control A"));			
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Figure f : drawContext.getModel().getFigures()) {
					drawContext.getView().addToSelection(f);
				}
				drawContext.getView().repaint();
				SelectionChangedListener.update();
			}
		});
	}
	
}
