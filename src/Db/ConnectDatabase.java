package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatabase {

	public ConnectDatabase() {

	}
	
	public static Connection connectDatabase() {
		
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/bobacool?useLegacyDatetimeCode=false&serverTimezone=UTC", "root",
					"");
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (connection == null) {
			System.out.println("Error");
		} else {
			System.out.println("Success");
		}
		
		return connection;
	}
}
