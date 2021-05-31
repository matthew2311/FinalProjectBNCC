import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import gui.Home;

public class Main {

	public static void main(String[] args) {
		
		UIManager.put("OptionPane.background", Color.black);
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.black);

		ImageIcon Welcome = new ImageIcon("welcome.gif");
		JOptionPane.showMessageDialog(null, " ", "Welcome", JOptionPane.INFORMATION_MESSAGE, Welcome);
		
		UIManager.put("OptionPane.background", Color.white);
		UIManager.getLookAndFeelDefaults().put("Panel.background", Color.white);
		
		new Home();
		
		
	}

}
