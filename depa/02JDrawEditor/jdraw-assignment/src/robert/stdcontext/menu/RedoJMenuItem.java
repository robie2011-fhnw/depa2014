package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import jdraw.framework.DrawContext;

public class RedoJMenuItem extends JMenuItem {
	public RedoJMenuItem(final DrawContext drawContext){
		super("Redo");
		setAccelerator(KeyStroke.getKeyStroke("control Y"));
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drawContext.getModel().getDrawCommandHandler().redo();
			}
		});
	}
}
