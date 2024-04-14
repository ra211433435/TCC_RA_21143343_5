package PastaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;


import PastaDTO.CadastroPgrDTO;

public class CadastroPgrDAO {
	
	Connection con;
	PreparedStatement pstm;
		
		
	public void CadastrarPgr(CadastroPgrDTO objPgrdto) throws SQLException {
	
		String sql = "insert into pgr (empresa, datainicial, datafinal) values (?,?,?)";
			
		con = ConexaoDAO.fazconexaoBD();
			
		try {
				
			pstm = con.prepareStatement(sql);
            pstm.setString(1, objPgrdto.getCadastro_pgrempresa());
            pstm.setString(2, objPgrdto.getCadastro_pgrdatainicial());           
            pstm.setString(3, objPgrdto.getCadastro_pgrdatafinal());
            
            pstm.execute();
            pstm.close();          
	            
			
				
		} catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"CadastroPGRDTO" + erro);
		}
		
	}
	
	

}
