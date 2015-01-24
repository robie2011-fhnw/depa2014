package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import jdraw.framework.DrawContext;

public abstract class DefaultJMenuItem extends JMenuItem {
	final DrawContext drawContext;
	public DefaultJMenuItem(final DrawContext drawContext, String menuName, KeyStroke key){
		super(menuName);
		this.drawContext = drawContext;
		if(key != null) setAccelerator(key);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				callOnActionPerformed();
			}
		});
	}
	public DefaultJMenuItem(final DrawContext drawContext, String menuName){
		this(drawContext,menuName,null);
	}
	abstract void callOnActionPerformed();
}
