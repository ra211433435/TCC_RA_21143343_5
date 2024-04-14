package PastaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import PastaDTO.CadastroCargoDTO;

public class CadastroCargoDAO {

	Connection con;
	PreparedStatement pstm;
		
		
	public void CadastrarCargo(CadastroCargoDTO objcadastrocargodto) throws SQLException {
	
		String sql = "insert into cargo (cargo, descricao) values (?,?)";
			
		con = ConexaoDAO.fazconexaoBD();
			
		try {
				
			pstm = con.prepareStatement(sql);
            pstm.setString(1, objcadastrocargodto.getCadastro_nomecargo());
            pstm.setString(2, objcadastrocargodto.getCadastro_decricaocargo());           
	            
            pstm.execute();
            pstm.close(); 
           
                      
				
		} catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"CadastroCargoDTO" + erro);
		}
		 con.close();
	}
	

}
