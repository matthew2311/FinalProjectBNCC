package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import List.Menu;

public class Form extends JPanel {
	
	Random Rand = new Random();
	
	private JPanel panel1, panel2, panel3;

	private JTextField NamaMenu, HargaMenu;

	private JComboBox StockMenu;

	public Form() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		panel1 = createPanel();
		panel2 = createPanel();
		panel3 = createPanel();

		// panel 1

		NamaMenu = new JTextField(20);
		panel1.add(new JLabel("Nama Menu"));
		panel1.add(NamaMenu);

		// panel 2

		HargaMenu = new JTextField(10);
		panel2.add(new JLabel("Harga Menu"));
		panel2.add(HargaMenu);

		// panel 3

		String[] Stock = { "Choice","10", "20", "30" , "40", "50" };

		StockMenu = new JComboBox(Stock);

		panel3.add(new JLabel("Stock Menu"));
		panel3.add(StockMenu);

		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
	}
	
	public void clearForm() {
		NamaMenu.setText("");

		HargaMenu.setText("");

		StockMenu.setSelectedIndex(0);
	}

	public boolean getCheck() {

		String name = NamaMenu.getText();
		if (name.trim().isEmpty()) {
			return false;
		}

		String Harga = HargaMenu.getText();

		if (Harga.trim().isEmpty()) {
			return false;
		}

		return true;
	}
	
	public void setFormData(Menu menu) {
		
		clearForm();
		
		NamaMenu.setText(menu.getNamaMenu());
		
		HargaMenu.setText(menu.getHargaMenu());
		
		StockMenu.setSelectedItem(menu.getStockMenu());
		
	}
	
	public Menu getNewMenu() {
		String Nama  = NamaMenu.getText();
		String Harga = HargaMenu.getText();

		if (!isNumber(Harga)) {
			JOptionPane.showMessageDialog(null, "Must be numeric!");
			return null;
		}else if(Integer.parseInt(Harga) <= 0) {
			JOptionPane.showMessageDialog(null, "Must be greater than 0!");
			return null;
		}
		
		int Stock = StockMenu.getSelectedIndex();
		if(Stock == 0) {
			JOptionPane.showMessageDialog(null, "Stock cannot be empty and must be exist!\n");
			return null;
		}
		
		if(Stock == 1) {
			Stock = 10;
		} else if(Stock == 2) {
			Stock = 20;
		} else if(Stock == 3) {
			Stock = 30;
		} else if(Stock == 4) {
			Stock = 40;
		} else if(Stock == 5) {
			Stock = 50;
		}
		
		return new Menu(Nama,Harga,Stock);
	}

	public Boolean isNumber(String Harga) {
		try {
			Integer.parseInt(Harga);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	private JPanel createPanel() {
		return new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 10));
	}
}
