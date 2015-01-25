package robert.stdcontext.menu;

import javax.swing.KeyStroke;

import jdraw.framework.DrawContext;
import robert.stdcontext.CommandListener;
import robert.stdcontext.Observer;

public class RedoJMenuItem extends DefaultJMenuItem implements Observer {
	public RedoJMenuItem(final DrawContext drawContext){
		super(drawContext, "Redo", KeyStroke.getKeyStroke("control Y"));
		setEnabled(false);
		CommandListener.addObserver(this);
	}

	@Override
	void callOnActionPerformed() {
		drawContext.getModel().getDrawCommandHandler().redo();
	}

	@Override
	public void update() {
		setEnabled(drawContext
						.getModel()
						.getDrawCommandHandler()
						.redoPossible());
	}
}
