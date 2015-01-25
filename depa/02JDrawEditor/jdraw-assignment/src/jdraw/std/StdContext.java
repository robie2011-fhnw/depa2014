/*
 * Copyright (c) 2000-2013 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.std;

import java.util.List;

import javax.swing.JMenu;

import jdraw.figures.GenericAbstractTool;
import jdraw.figures.Oval;
import jdraw.figures.Rect;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawToolFactory;
import jdraw.framework.DrawView;
import robert.stdcontext.menu.*;


/**
 * Standard implementation of interface DrawContext.
 * 
 * @see DrawView
 * @author Dominik Gruntz & Christoph Denzler
 * @version 2.6, 24.09.09
 */
public class StdContext extends AbstractContext {
	final DrawContext drawContext;
	
	/**
	 * Constructs a standard context with a default set of drawing tools.
	 * @param view the view that is displaying the actual drawing.
	 */
  public StdContext(DrawView view) {
		super(view, null);
		drawContext = getView().getDrawContext();
	}
	
  /**
   * Constructs a standard context. The drawing tools available can be parameterized using <code>toolFactories</code>.
   * @param view the view that is displaying the actual drawing.
   * @param toolFactories a list of DrawToolFactories that are available to the user
   */
	public StdContext(DrawView view, List<DrawToolFactory> toolFactories) {
		super(view, toolFactories);
		drawContext = getView().getDrawContext();
	}

	/**
	 * Creates and initializes the "Edit" menu.
	 * 
	 * @return the new "Edit" menu.
	 */
	@Override
	protected JMenu createEditMenu() {
		JMenu editMenu = new JMenu("Edit");
		editMenu.add(new UndoJMenuItem(drawContext));
		editMenu.add(new RedoJMenuItem(drawContext));
		editMenu.addSeparator();

		editMenu.add(new SelectAllJMenuItem(drawContext));
		editMenu.addSeparator();
		//editMenu.add("Cut").setEnabled(false);
		editMenu.add(new CopyJMenuItem(drawContext) );
		editMenu.add(new PasteJMenuItem(drawContext) );
		editMenu.addSeparator();
		editMenu.add(new GroupJMenuItem(drawContext));		
		editMenu.add(new UngroupJMenuItem(drawContext)) ;
		editMenu.addSeparator();

		JMenu orderMenu = new JMenu("Order...");
		orderMenu.add(new BringToFrontJMenuItem(drawContext));
		orderMenu.add(new SendToBackJMenuItem(drawContext));
		editMenu.add(orderMenu);

		JMenu grid = new JMenu("Grid...");
		grid.add(new FivePixelPointConstrainerJMenuItem(drawContext));
		grid.add("Grid 2");
		grid.add("Grid 3");
		editMenu.add(grid);
		
		// custom menues
		editMenu.addSeparator();
		editMenu.add(new LogDecoratorJMenuItem(drawContext));
		
		return editMenu;
	}

	/**
	 * Creates and initializes items in the file menu.
	 * 
	 * @return the new "File" menu.
	 */
	@Override
	protected JMenu createFileMenu() {
	  JMenu fileMenu = new JMenu("File");
		fileMenu.add(new OpenJMenuItem(drawContext));
		fileMenu.add(new SaveJMenuItem(drawContext));		
		fileMenu.add(new ExitJMenuItem());		
		return fileMenu;
	}

	@Override
	protected void doRegisterDrawTools() {
		// Add new figure tools here
		addTool(new GenericAbstractTool<Rect>(this, Rect.class));
		addTool(new GenericAbstractTool<Oval>(this, Oval.class));
	}



}
