package PastaDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import PastaDTO.UsuarioDTO;

public class UsuarioDAO {
    
    //Declaro uma variavel global fora do metodo para chamar qdo precisar
    
    Connection con;
    
    //Metodo
    public ResultSet autenticacaoUsuario(UsuarioDTO objusuariodto){
        
        new ConexaoDAO();
		try {
			con = ConexaoDAO.fazconexaoBD();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
            String sql = "select * from usuario where usuario = ? and senha = ? ";
           
            //Preparando a conexao 
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, objusuariodto.getNome_usuario());
            pstm.setString(2, objusuariodto.getSenha_usuario());           
            ResultSet rs = pstm.executeQuery();
            return rs;
            
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"UsuarioDAO" + erro);
            return null;
        }
        
        
    }

}
