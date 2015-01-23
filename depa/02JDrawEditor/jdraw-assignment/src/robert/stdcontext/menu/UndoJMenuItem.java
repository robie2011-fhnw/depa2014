package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import jdraw.framework.DrawContext;

public class UndoJMenuItem extends JMenuItem {
	public UndoJMenuItem(final DrawContext drawContext){
		super("Undo");
		setAccelerator(KeyStroke.getKeyStroke("control Z"));
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContext.getModel().getDrawCommandHandler().undo();
			}
		});
	}
}
