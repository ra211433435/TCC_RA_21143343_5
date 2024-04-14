package PastaDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDAO {
	
	public static Connection fazconexaoBD() throws SQLException{
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost/tcc_bd","root","MJ@324198Pef");
								
			
		} catch (ClassNotFoundException e) {
			
			throw new SQLException(e.getException());
			
			
		}
	}
	
}