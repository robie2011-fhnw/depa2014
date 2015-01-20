package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.JMenuItem;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

public class CopyJMenuItem extends JMenuItem {
	public CopyJMenuItem(final DrawContext drawContext) {
		super("Copy");
		setEnabled(true);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Copying ...");				
				if(drawContext.getView().getSelection() == null) return;
				LinkedList<Figure> copiedFigures = new LinkedList<Figure>();
				
				for(Figure f : drawContext.getView().getSelection())
					copiedFigures.add(f.clone());
								
				Clipboard.putInClipboard(copiedFigures);
			}
		});
	}
}
