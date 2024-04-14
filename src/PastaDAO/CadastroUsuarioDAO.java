package PastaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import PastaDTO.CadastroUsuarioDTO;

public class CadastroUsuarioDAO {

	Connection con;
	PreparedStatement pstm;
	
	public void CadastrarUsuario(CadastroUsuarioDTO objcadastrousuariodto) throws SQLException {
	
		String sql = "insert into usuario (usuario, senha) values (?,?)";
		
		con = ConexaoDAO.fazconexaoBD();
		
		try {
			
			pstm = con.prepareStatement(sql);
            pstm.setString(1, objcadastrousuariodto.getCadastro_nomeusuario());
            pstm.setString(2, objcadastrousuariodto.getCadastro_nomesenha());           
            
            pstm.execute();
            pstm.close();          
            
		
			
		} catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"CadastroUsuarioDTO" + erro);
		}
	
	}
}
