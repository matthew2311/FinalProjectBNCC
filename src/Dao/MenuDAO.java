package Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Vector;

import Db.ConnectDatabase;
import List.Menu;

public class MenuDAO {
	
	Random Rand = new Random();
	
	private Connection connection;
	
	public MenuDAO() {
		
		connection = ConnectDatabase.connectDatabase();
		
	}
	
	public void insert(Menu menu) {
		try {
			Statement stmt = connection.createStatement();
			
			String query = String.format("insert into tabelmenu values('%s', '%s', '%s', '%s' )", getKode(), menu.getNamaMenu(), menu.getHargaMenu(), menu.getStockMenu());
			
			stmt.execute(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String getKode() {
		String kode = "BC-";
		
		for(int x = 0 ; x < 3 ; x++) {
			kode += Rand.nextInt(10);
		}
		
		return kode;
	}
	
	public void update(Menu menu) {
		try {
			Statement stmt = connection.createStatement();
			
			String query = String.format("update tabelmenu set hargamenu = '%s', stockmenu = %d where kodemenu = '%s'",
					menu.getHargaMenu(), menu.getStockMenu(), menu.getKodeMenu());
			
			stmt.execute(query);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Vector<Vector<String>> getData(){
		
		Vector<Vector<String>> List = new Vector<Vector<String>>();
		
		try {
			Statement stmt = connection.createStatement();
			
			String query = "select * from tabelmenu";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				Vector<String> subList = new Vector<String>();
				
				subList.add(rs.getString(1));
				subList.add(rs.getString(2));
				subList.add(rs.getString(3));
				subList.add(rs.getString(4));
				
				List.add(subList);
			}
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		
		return List;
	}
	
public Vector<String> getKodee(){
		
		Vector<String> List = new Vector();
		
		try {
			Statement stmt = connection.createStatement();
			
			String query = "select kodemenu from tabelmenu ";
			
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				
				
				List.add(rs.getString(1));
			}
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		
		return List;
	}

	public void delete (String Kode) {
		try {
			Statement stmt = connection.createStatement();
			
			String query = "delete from tabelmenu where kodemenu = '"+Kode+"'";
					
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
