package gui;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Dao.MenuDAO;
import List.Menu;

public class Update extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private JTable table;

	private JScrollPane Scroll;

	private Form form;

	private DefaultTableModel model;

	private MenuDAO dao;

	private JButton Back, Update, Discard;

	private JPanel panel1, panel2;

	private JTextField HargaMenu, StockMenu;

	private Vector<String> title;

	public Update() {
		dao = new MenuDAO();
		form = new Form();
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		ImageIcon Image = new ImageIcon("ImageIcon.jpg");
		this.setIconImage(Image.getImage());
		this.setTitle("Update Menu BobaCool");

		ViewMenu();
		panel1 = createPanel();
		panel2 = createPanel();

		HargaMenu = new JTextField(10);
		panel1.add(new JLabel("Harga Menu"));
		panel1.add(HargaMenu);

		StockMenu = new JTextField(5);
		panel2.add(new JLabel("Stock Menu"));
		panel2.add(StockMenu);

		this.add(panel1);
		this.add(panel2);

		Button();

		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void Button() {

		Back = new JButton("Back");
		Update = new JButton("Update");
		Discard = new JButton("Discard Change");

		Back.addActionListener(this);
		Update.addActionListener(this);
		Discard.addActionListener(this);

		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout());

		panel.add(Back);
		panel.add(Box.createHorizontalStrut(100));
		panel.add(Update);
		panel.add(Discard);

		this.add(panel);
	}

	private void ViewMenu() {
		title = new Vector<String>();
		title.add("Kode Menu");
		title.add("Nama Menu");
		title.add("Harga Menu");
		title.add("Stock Menu");

		model = new DefaultTableModel(dao.getData(), title) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(model);
		table.getColumnModel().getColumn(0).setPreferredWidth(2);
		table.getColumnModel().getColumn(2).setPreferredWidth(3);
		table.getColumnModel().getColumn(3).setPreferredWidth(2);
		
		table.addMouseListener(this);
		Scroll = new JScrollPane(table);

		this.add(Scroll);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Back) {
			new Home();
			this.dispose();
		} else if (e.getSource() == Update) {

			String Check = getCheck();

			if (Check == null) {
				JOptionPane.showMessageDialog(null, "Error !! \nAll the field must be fill!");
			} else {

				int letak = table.getSelectedRow();

				Menu menu = getNewMenu();
				String Kode = table.getValueAt(letak, 0).toString();
				menu.SetKodeMenu(Kode);

				dao.update(menu);

				JOptionPane.showMessageDialog(null, "Success to update " + Kode);

				
				HargaMenu.setText("");
				StockMenu.setText("");
				
				model.setDataVector(dao.getData(), title);
				table.getColumnModel().getColumn(0).setPreferredWidth(2);
				table.getColumnModel().getColumn(2).setPreferredWidth(3);
				table.getColumnModel().getColumn(3).setPreferredWidth(2);


			}
		} else if (e.getSource() == Discard) {
			setFormData(getDataFromTable());
		}

	}

	public String getCheck() {
		boolean valid = true;
		String Harga = HargaMenu.getText();
		String Stock = StockMenu.getText();

		if (Harga.trim().isEmpty()) {
			valid = false;
		}

		if (Stock.trim().isEmpty()) {
			valid = false;
		}

		if (!valid) {
			return null;
		}

		return Harga;
	}

	public Menu getNewMenu() {
		String Harga = HargaMenu.getText();
		String Stock = StockMenu.getText();

		int letak = table.getSelectedRow();
		String Nama = table.getValueAt(letak, 1).toString();

		if (!isNumber(Harga)) {
			JOptionPane.showMessageDialog(null, "Harga Menu Must be numeric!");
			return null;
		} else if (Integer.parseInt(Harga) <= 0) {
			JOptionPane.showMessageDialog(null, "Harga Menu Must be greater than 0!");
			return null;
		}

		if (!isNumber(Stock)) {
			JOptionPane.showMessageDialog(null, "Stock Menu Must be numeric!");
			return null;
		} else if (Integer.parseInt(Stock) <= 0) {
			JOptionPane.showMessageDialog(null, "Stock Menu Must be greater than 0!");
			return null;
		}

		return new Menu(Nama, Harga, Integer.parseInt(Stock));
	}

	public Boolean isNumber(String Harga) {
		try {
			Integer.parseInt(Harga);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public void setFormData(Menu menu) {
		
		HargaMenu.setText(menu.getHargaMenu());
		
		StockMenu.setText(Integer.toString(menu.getStockMenu()));
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int letak = table.rowAtPoint(e.getPoint());

		if (letak >= 0 && letak < table.getRowCount()) {
			table.setRowSelectionInterval(letak, letak);

			form.setFormData(getDataFromTable());
		} else {
			table.clearSelection();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private Menu getDataFromTable() {

		int letak = table.getSelectedRow();

		String Kode = table.getValueAt(letak, 0).toString();
		String Nama = table.getValueAt(letak, 1).toString();
		String Harga = table.getValueAt(letak, 2).toString();
		int Stock = Integer.parseInt(table.getValueAt(letak, 3).toString());

		Menu menu = new Menu(Nama, Harga, Stock);
		menu.SetKodeMenu(Kode);

		return menu;
	}

	private JPanel createPanel() {
		return new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 10));
	}
}
