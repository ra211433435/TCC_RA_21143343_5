package PastaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import PastaDTO.CadastroFuncionarioDTO;

public class CadastroFuncionarioDAO {
	
	Connection con;
	PreparedStatement pstm;
	ResultSet rs;	
	
	public void CadastrarFuncionario(CadastroFuncionarioDTO objfuncionariodto) throws SQLException {
	
		String sql = "insert into funcionario (funcionarioempresa, nome, funcionariocargo) values (?,?,?)";
			
		con = ConexaoDAO.fazconexaoBD();
			
		try {
				
			pstm = con.prepareStatement(sql);
            pstm.setString(1, objfuncionariodto.getCadastro_funcionarioempresa());
            pstm.setString(2, objfuncionariodto.getCadastro_funcionarionome());           
            pstm.setString(3, objfuncionariodto.getCadastro_funcionariocargo());
            
            pstm.execute();
            pstm.close();          
						
		} catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"CadastrarFuncionárioAO" + erro);
		}
	}
}			
			
