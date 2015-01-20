package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import jdraw.framework.DrawContext;
import robert.pointconstrainer.FivePixelPointConstrainer;

public class FivePixelPointConstrainerJMenuItem extends JMenuItem {

	public FivePixelPointConstrainerJMenuItem(final DrawContext ctx) {
		super("5px Grid");
		setEnabled(true);
		
		addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(ctx.getView().getConstrainer() != null)
					ctx.getView().setConstrainer(null);
				else
					ctx.getView().setConstrainer(new FivePixelPointConstrainer());
				
			}
		});
	}
}
