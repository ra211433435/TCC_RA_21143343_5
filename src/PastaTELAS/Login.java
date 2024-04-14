package PastaTELAS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import PastaDAO.UsuarioDAO;
import PastaDTO.UsuarioDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField psSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Acesso ao Sistema");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(149, 11, 139, 27);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(180, 83, 128, 27);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblUsuario = new JLabel("Usuário");
		lblUsuario.setBounds(84, 89, 46, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(84, 135, 46, 14);
		contentPane.add(lblSenha);
		
		psSenha = new JPasswordField();
		psSenha.setBounds(180, 129, 128, 27);
		contentPane.add(psSenha);
		
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {

		            //Declarando as variaveis de acessa DTO    
		            String nome_usuario;
		            String senha_usuario;

		            nome_usuario = txtUsuario.getText();
		            senha_usuario = new String(psSenha.getPassword());

		            //Acessando a classe DTO
		            UsuarioDTO objusuariodto = new UsuarioDTO();
		            objusuariodto.setNome_usuario(nome_usuario);
		            objusuariodto.setSenha_usuario(senha_usuario);

		            //Executando a classe DAO 
		            
		            UsuarioDAO objUsuarioDAO = new UsuarioDAO();
		            ResultSet rsusuarioDAO = objUsuarioDAO.autenticacaoUsuario(objusuariodto);
		            
		            if (rsusuarioDAO.next()) {
		                //Chamar tela que eu quero abrir
		                TelaMenu objTelaMenu = new TelaMenu();
		                objTelaMenu.setVisible(true);
		                
		                dispose();
		                
		            }else{
		                //Enviar menssagem que usuario e senha estão incorretos
		                JOptionPane.showMessageDialog(null, "Usuário ou Senha Inválida");
		            }
		            
		            
		        } catch (SQLException erro) {
		            JOptionPane.showMessageDialog(null, "frmTelaLogin" + erro);
		        }
				
				
				
				
			}
		});
		btnEntrar.setBounds(149, 204, 139, 27);
		contentPane.add(btnEntrar);
	}
}
