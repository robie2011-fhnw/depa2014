package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

public class SaveJMenuItem extends JMenuItem {
	public SaveJMenuItem(final DrawContext drawContext){
		super("Save");
		setAccelerator(KeyStroke.getKeyStroke("control S"));
		addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				doSave(drawContext.getModel().getFigures());
			}
		});
	}
	
	/**
	 * Handles the saving of a drawing to a file.
	 */
	private void doSave(Iterable<Figure> figures) {
		JFileChooser chooser = new JFileChooser(getClass().getResource("")
				.getFile());
		chooser.setDialogTitle("Save Graphic");
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
		FileFilter filter = new FileFilter() {
			@Override
			public String getDescription() {
				return "JDraw Graphic (*.draw)";
			}

			@Override
			public boolean accept(File f) {
				return f.getName().endsWith(".draw");
			}
		};
		chooser.setFileFilter(filter);
		int res = chooser.showOpenDialog(this);

		if (res == JFileChooser.APPROVE_OPTION) {
			// save graphic
			File file = chooser.getSelectedFile();
			if (chooser.getFileFilter() == filter && !filter.accept(file)) {
				file = new File(chooser.getCurrentDirectory(), file.getName() + ".draw");
			}
			System.out.println("save current graphic to file " + file.getName());
			
			// write to file
			ObjectOutputStream objectOutputStream;
			try {
				objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
				
				for(Figure figure : figures){
					objectOutputStream.writeObject(figure.clone());
				}
				objectOutputStream.writeObject(null); // signaling end of file
				objectOutputStream.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
