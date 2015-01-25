package robert.stdcontext.menu;

import javax.swing.KeyStroke;

import robert.stdcontext.CommandListener;
import robert.stdcontext.Observer;
import jdraw.framework.DrawContext;

public class UndoJMenuItem extends DefaultJMenuItem implements Observer {
	public UndoJMenuItem(final DrawContext drawContext){
		super(drawContext, "Undo", KeyStroke.getKeyStroke("control Z"));
		setEnabled(false);
		CommandListener.addObserver(this);
	}

	@Override
	void callOnActionPerformed() {
		drawContext.getModel().getDrawCommandHandler().undo();		
	}

	@Override
	public void update() {
		setEnabled( drawContext.getModel()
								.getDrawCommandHandler()
								.undoPossible());
	}
}
