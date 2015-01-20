package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;
import robert.figuregroup.FigureGroupConcrete;

public class GroupJMenuItem extends JMenuItem {

	public GroupJMenuItem(final DrawContext ctx) {
		super("Group");
		setEnabled(true);
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Figure> selectedFigures = ctx.getView().getSelection();
				if(selectedFigures instanceof FigureGroupConcrete == false) {					
					new FigureGroupConcrete(ctx.getView().getDrawContext());
				}				
			}
		});
	}
}