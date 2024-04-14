package PastaTELAS;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import PastaDAO.CadastroUsuarioDAO;
import PastaDAO.ConexaoDAO;
import PastaDTO.CadastroUsuarioDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class TelaUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCadastroUsuario;
	private JTextField txtCadastroSenha;
	private JTable tbListarUsuario;
	private JTextField txtUsuarioId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario frame = new TelaUsuario();
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
	public TelaUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblControleDosUsuarios = new JLabel("Controle dos Usuarios");
		lblControleDosUsuarios.setFont(new Font("Dialog", Font.BOLD, 14));
		lblControleDosUsuarios.setEnabled(true);
		lblControleDosUsuarios.setBounds(130, 0, 175, 35);
		contentPane.add(lblControleDosUsuarios);
		
		JLabel lblCadastroUsuario = new JLabel("Usuario");
		lblCadastroUsuario.setBounds(36, 138, 81, 14);
		contentPane.add(lblCadastroUsuario);
		
		JLabel lblCadastroSenha = new JLabel("Senha");
		lblCadastroSenha.setBounds(36, 188, 81, 14);
		contentPane.add(lblCadastroSenha);
		
		txtCadastroUsuario = new JTextField();
		txtCadastroUsuario.setColumns(10);
		txtCadastroUsuario.setBounds(36, 154, 135, 23);
		contentPane.add(txtCadastroUsuario);
		
		txtCadastroSenha = new JTextField();
		txtCadastroSenha.setColumns(10);
		txtCadastroSenha.setBounds(36, 205, 135, 23);
		contentPane.add(txtCadastroSenha);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				if(txtCadastroUsuario.getText().equals("") ||txtCadastroSenha.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Nome do Usuário ou Senha está em branco!");
				
				}else {
					
				String cadastro_usuario, cadastro_senha;
				
				cadastro_usuario = txtCadastroUsuario.getText();
				cadastro_senha = txtCadastroSenha.getText();
				
				CadastroUsuarioDTO objcadastrousuariodto = new CadastroUsuarioDTO();
				objcadastrousuariodto.setCadastro_nomeusuario(cadastro_usuario);
				objcadastrousuariodto.setCadastro_nomesenha(cadastro_senha);
				
				CadastroUsuarioDAO objcadastrousuariodao = new CadastroUsuarioDAO();
				
				try {
					objcadastrousuariodao.CadastrarUsuario(objcadastrousuariodto);
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
					
					txtCadastroUsuario.setText("");
					txtCadastroSenha.setText("");
					
					
				} catch (SQLException erro) {
					JOptionPane.showMessageDialog(null, "Erro cadastro Usuário - tela!" + erro);
					// TODO Auto-generated catch block
					erro.printStackTrace();
				
				}
				
				}
				}
		});
		btnNewButton.setBounds(309, 95, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnListarUsuario = new JButton("Listar");
		btnListarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
										
					try {
						
						Connection con;
						new ConexaoDAO();
						con = ConexaoDAO.fazconexaoBD();
						
						String sql = "Select * from usuario";
						PreparedStatement pstm = con.prepareStatement(sql);
						
						ResultSet rs = pstm.executeQuery();
						
						DefaultTableModel model = (DefaultTableModel) tbListarUsuario.getModel();
						model.setNumRows(0);
						
						while (rs.next()) {
							
							model.addRow(new Object[]{rs.getString("idusuario"), rs.getString("usuario"), rs.getString("senha")});
							
						}
						rs.close();
						con.close();
									
					} catch (Exception e2) {
						// TODO: handle exception
					} 			
				
			}
		});
		btnListarUsuario.setBounds(309, 229, 89, 23);
		contentPane.add(btnListarUsuario);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 251, 362, 103);
		contentPane.add(scrollPane);
		
		tbListarUsuario = new JTable();
		tbListarUsuario.setShowGrid(false);
		tbListarUsuario.setShowHorizontalLines(false);
		tbListarUsuario.setShowVerticalLines(false);
		tbListarUsuario.setForeground(new Color(0, 0, 0));
		tbListarUsuario.setEnabled(false);
		tbListarUsuario.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Id", "Usuário", "Senha"
			}
		));
		tbListarUsuario.getColumnModel().getColumn(0).setPreferredWidth(33);
		tbListarUsuario.getColumnModel().getColumn(1).setPreferredWidth(124);
		tbListarUsuario.getColumnModel().getColumn(2).setPreferredWidth(83);
		scrollPane.setViewportView(tbListarUsuario);
		
		JButton btnAtualizarcarPcmso = new JButton("Atualizar");
		btnAtualizarcarPcmso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtUsuarioId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");					
					
				} else {
								
				try {
					Connection con;
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "update usuario set usuario=?, senha=? where idusuario=?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
				
						pstm.setString(1, txtCadastroUsuario.getText());
						pstm.setString(2, txtCadastroSenha.getText());
						pstm.setString(3, txtUsuarioId.getText());
						
						pstm.execute();
						
						pstm.close();						
						con.close();
								
						JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");	
					
						txtUsuarioId.setText("");
						txtCadastroUsuario.setText("");
						txtCadastroSenha.setText("");				
						
				} catch (Exception e2) {
					// TODO: handle exception
				}
				}
			}
		});
		btnAtualizarcarPcmso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAtualizarcarPcmso.setBounds(309, 129, 89, 23);
		contentPane.add(btnAtualizarcarPcmso);
		
		JButton btnExcluiPcmso = new JButton("Excluir");
		btnExcluiPcmso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con;						
					new ConexaoDAO();
					con = ConexaoDAO.fazconexaoBD();
					
					String sql = "delete from usuario where idusuario=?";
					
					PreparedStatement pstm = con.prepareStatement(sql);
					pstm.setString(1, txtUsuarioId.getText());
					
					pstm.execute();
					
					pstm.close();
					con.close();
					
					JOptionPane.showMessageDialog(null, "Dados excluidos");
					
					txtUsuarioId.setText("");
					txtCadastroUsuario.setText("");
					txtCadastroSenha.setText("");
								
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			
				}			
			}
		});
		btnExcluiPcmso.setBounds(309, 162, 89, 23);
		contentPane.add(btnExcluiPcmso);
		
		JButton btnAbrirPgrId = new JButton("Id");
		btnAbrirPgrId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtUsuarioId.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Informe o Id");
					
				}else {
					try {
						Connection con;						
						new ConexaoDAO();
						con = ConexaoDAO.fazconexaoBD();
						
						String sql = "select * from usuario where idusuario like?";
						
						PreparedStatement pstm = con.prepareStatement(sql);
						pstm.setString(1, "%" + txtUsuarioId.getText());
					
						ResultSet rs = pstm.executeQuery();
						
						while (rs.next()) {
							
							txtUsuarioId.setText(rs.getString("idusuario"));
							txtCadastroUsuario.setText(rs.getString("usuario"));
							txtCadastroSenha.setText(rs.getString("senha"));
						}
						
						rs.close();
						con.close();

					} catch (Exception e1) {
					// TODO: handle exception
				}
			}
			}
		});
		btnAbrirPgrId.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAbrirPgrId.setBounds(36, 79, 46, 23);
		contentPane.add(btnAbrirPgrId);
		
		txtUsuarioId = new JTextField();
		txtUsuarioId.setColumns(10);
		txtUsuarioId.setBounds(82, 79, 46, 22);
		contentPane.add(txtUsuarioId);
	}
}
