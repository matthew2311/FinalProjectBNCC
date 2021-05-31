package gui;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Dao.MenuDAO;
import List.Menu;

public class View extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private JTable table;

	private JScrollPane Scroll;

	private MenuDAO dao;
	
	private JButton Back;
	
	private DefaultTableModel model;

	private Vector<String> title;

	public View() {
		dao = new MenuDAO();
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		ImageIcon Image = new ImageIcon("ImageIcon.jpg");
		this.setIconImage(Image.getImage());
		
		this.setTitle("View Menu BobaCool");
		
		ViewMenu();
		
		Button();

		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void Button() {

		Back = new JButton("Back");

		Back.addActionListener(this);

		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout());

		panel.add(Box.createHorizontalStrut(300));
		panel.add(Back);

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
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Back) {
			new Home();
			this.dispose();
		}

	}

}
