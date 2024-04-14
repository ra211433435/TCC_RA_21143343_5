package PastaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import PastaDTO.CadastroEmpresaDTO;

public class CadastroEmpresaDAO {
	
	Connection con;
	PreparedStatement pstm;
	
	
	public void CadastrarEmpresa(CadastroEmpresaDTO objcadastroempresadto) throws SQLException {
	
		String sql = "insert into empresa (cnpj, nome) values (?,?)";
		
		con = ConexaoDAO.fazconexaoBD();
		
		try {
			
			pstm = con.prepareStatement(sql);
            pstm.setString(1, objcadastroempresadto.getCadastro_cnpjempresa());
            pstm.setString(2, objcadastroempresadto.getCadastro_nomeempresa());           
            
            pstm.execute();
            pstm.close();          
            con.close();
		
			
		} catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"CadastroEmpresaDAO" + erro);
		}
	
	}
	

}
