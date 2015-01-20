package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

public class PasteJMenuItem extends JMenuItem {
	public PasteJMenuItem(final DrawContext ctx){		
		super("Paste");
		
		setEnabled(true);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Pasting ...");
				if(Clipboard.getFromClipboard() == null) return;
				
				for(Figure f : Clipboard.getFromClipboard()){					
					ctx.getModel().addFigure(f);
					ctx.getView().addToSelection(f);
				}
				
				Clipboard.putInClipboard(null);
			}
		});
	}
}
