package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import jdraw.framework.DrawContext;

public abstract class DefaultJMenuItem extends JMenuItem {
	public DefaultJMenuItem(final DrawContext drawContex, String menuName, KeyStroke key ){
		super(menuName);
		
		if(key != null) setAccelerator(key);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				callOnActionPerformed(drawContex);
			}
		});
	}
	
	abstract void callOnActionPerformed(DrawContext drawContex);
}
