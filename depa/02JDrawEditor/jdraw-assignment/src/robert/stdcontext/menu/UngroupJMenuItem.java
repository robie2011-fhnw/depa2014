package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import robert.figuregroup.FigureGroupConcrete;
import robert.stdcontext.Observer;

public class UngroupJMenuItem extends JMenuItem implements Observer {

	public UngroupJMenuItem(final DrawContext drawContext) {
		super("Ungroup");
		setEnabled(true);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Figure> selectedFigures = drawContext.getView().getSelection();
				for(Figure f : selectedFigures){
					if(f instanceof FigureGroupConcrete)
						((FigureGroupConcrete) f).ungroup();					
				}
			}
		});
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
