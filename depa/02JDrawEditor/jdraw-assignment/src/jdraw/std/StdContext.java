/*
 * Copyright (c) 2000-2013 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.std;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import jdraw.figures.GenericAbstractTool;
import jdraw.figures.Oval;
import jdraw.figures.Rect;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawToolFactory;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import robert.stdcontext.SelectionChangedListener;
import robert.stdcontext.menu.BringToFrontJMenuItem;
import robert.stdcontext.menu.CopyJMenuItem;
import robert.stdcontext.menu.ExitJMenuItem;
import robert.stdcontext.menu.FivePixelPointConstrainerJMenuItem;
import robert.stdcontext.menu.GroupJMenuItem;
import robert.stdcontext.menu.OpenJMenuItem;
import robert.stdcontext.menu.PasteJMenuItem;
import robert.stdcontext.menu.RedoJMenuItem;
import robert.stdcontext.menu.SaveJMenuItem;
import robert.stdcontext.menu.SelectAllJMenuItem;
import robert.stdcontext.menu.SendToBackJMenuItem;
import robert.stdcontext.menu.UndoJMenuItem;
import robert.stdcontext.menu.UngroupJMenuItem;

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

	// TODO: Refactoring, move to separate class
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
