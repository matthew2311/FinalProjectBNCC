package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Home extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JLabel label1;
	
	private JMenuBar menuBar;
	
	private JMenu Home, Edit;
	
	private JMenuItem Add, Update, View , Delete;

	public Home() {

		Label();
	
		ImageIcon image = new ImageIcon("Imageicon.jpg");
		this.setIconImage(image.getImage());
		
		Menu();
		this.setTitle("BobaCool");
		this.pack();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void Label() {
		label1 = new JLabel();
		
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setVerticalAlignment(JLabel.CENTER);
		label1.setHorizontalTextPosition(JLabel.CENTER);
		label1.setVerticalTextPosition(JLabel.TOP);
		label1.setText("BobaCool");
		
		label1.setForeground(new Color(255,255,255));
		label1.setFont(new Font("Chiller", Font.BOLD, 100));
		label1.setBackground(Color.black);
		label1.setOpaque(true);
		
		Image image2;
		try {
			image2 = ImageIO.read(new File("icon.jpg"));
			Image image3 = image2.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(image3);
			label1.setIcon(icon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.add(label1);
	}
	
	private void Menu() {
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		
		Home = new JMenu("Home");
		Edit = new JMenu("Edit");
		
		Add = new JMenuItem("Add");
		View = new JMenuItem("View");
		Update = new JMenuItem("Update");
		Delete = new JMenuItem("Delete");
		
		Add.addActionListener(this);
		View.addActionListener(this);
		Update.addActionListener(this);
		Delete.addActionListener(this);
		
		Home.add(Add);
		Home.add(View);
		
		Edit.add(Update);
		Edit.add(Delete);
		
		menuBar.add(Home);
		menuBar.add(Edit);
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == Add) {
			new Add();
			this.dispose();
		}else if(e.getSource() == View) {
			new View();
			this.dispose();
		}else if(e.getSource() == Update) {
			new Update();
			this.dispose();
		}else if(e.getSource() == Delete) {
			new Delete();
			this.dispose();
		}
		
	}

}
