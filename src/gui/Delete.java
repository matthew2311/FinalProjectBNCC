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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Dao.MenuDAO;
import List.Menu;

public class Delete extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;

	private JTable table;

	private JScrollPane Scroll;

	private JPanel panel1;

	private JTextField KodeText;

	private MenuDAO dao;

	private JButton Back, Delete;

	private DefaultTableModel model;

	private Vector<String> title;

	public Delete() {
		dao = new MenuDAO();
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

		ImageIcon Image = new ImageIcon("ImageIcon.jpg");
		this.setIconImage(Image.getImage());

		this.setTitle("View Menu BobaCool");

		ViewMenu();

		Delete();

		Button();

		this.setSize(400, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	private void Delete() {

		panel1 = createPanel();
		KodeText = new JTextField(10);
		panel1.add(new JLabel("Kode Menu"));
		panel1.add(KodeText);

		this.add(panel1);

	}

	private void Button() {

		Delete = new JButton("Delete");
		Back = new JButton("Back");

		Delete.addActionListener(this);
		Back.addActionListener(this);

		JPanel panel = new JPanel();

		panel.setLayout(new FlowLayout());

		panel.add(Box.createHorizontalStrut(200));
		panel.add(Delete);
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
		} else if (e.getSource() == Delete) {
			String Kode = CheckKode();

			if (Kode == null) {
				JOptionPane.showMessageDialog(null, "Error !! \nKode Menu must be fill!");
			} else {
					String pengecek = CheckKode2(Kode);
					if(pengecek == null) {
						JOptionPane.showMessageDialog(null,  Kode + " doesn't exists");
						KodeText.setText("");
					}else {
						dao.delete(Kode);

						JOptionPane.showMessageDialog(null, "Success to delete " + Kode + " !");

						KodeText.setText("");
						
						model.setDataVector(dao.getData(), title);
						table.getColumnModel().getColumn(0).setPreferredWidth(2);
						table.getColumnModel().getColumn(0).setPreferredWidth(2);
						table.getColumnModel().getColumn(2).setPreferredWidth(3);
						table.getColumnModel().getColumn(3).setPreferredWidth(2);
					}
			}
		}

	}

	public String CheckKode() {
		boolean valid = true;
		String Kode = KodeText.getText();
		if (Kode.trim().isEmpty()) {
			valid = false;
		}

		if (!valid) {
			return null;
		}

		return Kode;
	}

	public String CheckKode2(String Kode) {
		boolean valid = false;
		Vector<String> data = new Vector();
		
		data.addAll(dao.getKodee());
		
		for(int x = 0 ; x < data.size() ; x++) {
			if(data.get(x).equals(Kode)) {
				valid = true;
			}
		}
		
		if(valid) {
			return Kode;
		}else {
			return null;
		}
	}
	
	private JPanel createPanel() {
		return new JPanel(new FlowLayout(FlowLayout.LEADING, 20, 10));
	}

}
