package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

public class OpenJMenuItem extends JMenuItem {
	public OpenJMenuItem(final DrawContext drawContext){
		super("Open");
		setAccelerator(KeyStroke.getKeyStroke("control O"));
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doOpen(drawContext.getModel());
			}
		});
	}
	
	/**
	 * Handles the opening of a new drawing from a file.
	 */
	private void doOpen(DrawModel drawModel) {
		JFileChooser chooser = new JFileChooser(getClass().getResource("")
				.getFile());
		chooser.setDialogTitle("Open Graphic");
		chooser.setDialogType(JFileChooser.OPEN_DIALOG);
		chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
			@Override
			public String getDescription() {
				return "JDraw Graphic (*.draw)";
			}

			@Override
			public boolean accept(File f) {
				return f.isDirectory() || f.getName().endsWith(".draw");
			}
		});
		int res = chooser.showOpenDialog(this);

		if (res == JFileChooser.APPROVE_OPTION) {
			// read jdraw graphic
			System.out.println("read file "
					+ chooser.getSelectedFile().getName());
			
			try {
				ObjectInputStream objectInputStream 
						= new ObjectInputStream(new FileInputStream(chooser.getSelectedFile()));
				
				Figure savedFigure;
				try {
					while( (savedFigure = (Figure) objectInputStream.readObject()) != null ){
						drawModel.addFigure(savedFigure);
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
