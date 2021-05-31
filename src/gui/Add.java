package gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Dao.MenuDAO;
import List.Menu;

public class Add extends JFrame implements ActionListener {

	private JPanel panel;

	private JButton Cancel, Add;
	
	private Form form;
	
	private MenuDAO dao;

	public Add() {
		
		ImageIcon image = new ImageIcon("ImageIcon.jpg");
		this.setIconImage(image.getImage());
		
		dao = new MenuDAO();
		form = new Form();
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.setTitle("Add Menu BobaCool");
		panel = new JPanel(new FlowLayout(FlowLayout.TRAILING, 20, 10));
		
		Cancel = new JButton("Cancel");
		Add = new JButton("Add");
		Add.addActionListener(this);
		Cancel.addActionListener(this);
			
		panel.add(Add);
		panel.add(Cancel);
		
		this.add(form);
		this.add(panel);
		
		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setBackground(Color.black);
		this.setVisible(true);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Cancel) {
			new Home();
			this.dispose();
		}else if(e.getSource() == Add) {
			boolean check;
			check  = form.getCheck();
			
			if(check == true) {
				Menu newMenu = form.getNewMenu();
				
				if(newMenu != null) {
					
					dao.insert(newMenu);
					
					JOptionPane.showMessageDialog(null, "Succes to add " + newMenu.getNamaMenu());
					
					form.clearForm();
				}
			}else if(check == false) {
				JOptionPane.showMessageDialog(null, "All the field must be fill!");
			}
			
		}
	}
	
}
