package PastaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import PastaDTO.CadastroPcmsoDTO;

public class CadastroPcmsoDAO {
	
	Connection con;
	PreparedStatement pstm;
		
		
	public void CadastrarPcmso(CadastroPcmsoDTO objPcmsodto) throws SQLException {
	
		String sql = "insert into pcmso (empresa, datainicial, datafinal) values (?,?,?)";
			
		con = ConexaoDAO.fazconexaoBD();
			
		try {
				
			pstm = con.prepareStatement(sql);
            pstm.setString(1, objPcmsodto.getCadastro_pcmsoempresa());
            pstm.setString(2, objPcmsodto.getCadastro_pcmsodatainicial());           
            pstm.setString(3, objPcmsodto.getCadastro_pcmsodatafinal());
            
            pstm.execute();
            pstm.close();          
	            
			
				
		} catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"CadastroPcmsoDAO" + erro);
		}
	}
}
