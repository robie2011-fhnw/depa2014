package robert.stdcontext.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class ExitJMenuItem extends JMenuItem {
	public ExitJMenuItem(){
		super("Exit");
		
		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}
	
}
